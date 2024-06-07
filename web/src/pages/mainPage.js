import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import MusicPlaylistClient from '../api/musicPlaylistClient';

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
        this.client = new MusicPlaylistClient();

    }

    createWatchlist(evt) {
        evt.preventDefault();

        const watchlistName = document.getElementById("createWatchlistInput").value;

        if (watchlistName.trim() === "") {
            alert("Watchlist name cannot be empty");
            return;
        }

       const stockSymbols = document.getElementById("createStockSymbolsList").value;

       if (stockSymbols.trim() === "") {
                    alert("Watchlist name cannot be empty");
                    return;
       }

       const stockSymbolsArray =  stockSymbols.split(",").map(symbol => symbol.trim());

        this.client.createWatchlist(watchlistName,stockSymbols);

        const li = document.createElement("li");
        li.textContent = watchlistName;

        document.getElementById("createWatchlistUl").appendChild(li);

        document.getElementById("createWatchlistInput").value = "";
    }

    clearWatchlist(evt) {
        evt.preventDefault();
        document.getElementById("createWatchlistInput").value = "";
    }

    deleteWatchlist(evt) {
        evt.preventDefault();
        const watchlistUl = document.getElementById("createWatchlistUl");
        while (watchlistUl.firstChild) {
            watchlistUl.removeChild(watchlistUl.firstChild);
        }
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


