import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import StockWatchListClient from '../api/stockWatchListClient';

class MainPage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'createWatchlist', 'clearWatchlist', 'deleteWatchlist'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('createButton').addEventListener('click', this.createWatchlist);
        document.getElementById('clearButton').addEventListener('click', this.clearWatchlist);
        document.getElementById('deleteButton').addEventListener('click', this.deleteWatchlist);

        this.header.addHeaderToPage();
        this.client = new StockWatchListClient();

    }

    createWatchlist(evt) {
        evt.preventDefault();

        const watchlistName = document.getElementById("watchlistInput").value;

        if (watchlistName.trim() === "") {
            alert("Watchlist name cannot be empty");
            return;
        }

        const stockSymbols = document.getElementById("stockSymbolsInput").value;

       if (stockSymbols.trim() === "") {
                    alert("Watchlist name cannot be empty");
                    return;
       }

       const stockSymbolsArray =  stockSymbols.split(",");

        this.client.createWatchlist(watchlistName,stockSymbolsArray);

        const li = document.createElement("li");
        li.textContent = watchlistName;

        document.getElementById("watchlistUl").appendChild(li);

        document.getElementById("watchlistInput").value = "";
        document.getElementById("stockSymbolsInput").value ="";
    }

    clearWatchlist(evt) {
        evt.preventDefault();
        document.getElementById("watchlistInput").value = "";
        document.getElementById("stockSymbolsInput").value = "";
    }

    deleteWatchlist(evt) {
        evt.preventDefault();

        const watchlistName = document.getElementById("watchlistInput").value;

        if (watchlistName.trim() === "") {
            alert("Watchlist name cannot be empty");
            return;
        }

        this.client.deleteWatchlist(watchlistName);

        const li = document.createElement("li");
        li.textContent = watchlistName;

        document.getElementById("watchlistUl").appendChild(li);

        document.getElementById("watchlistInput").value ="";
    }

    



}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const mainPage = new MainPage();
    mainPage.mount();
};

window.addEventListener('DOMContentLoaded', main);


