import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import StockWatchListClient from '../api/stockWatchListClient';

class MainPage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'createWatchlist', 'clearWatchlist', 'deleteWatchlist','addWatchlistToPage','redirectToStockInfo','clientLoaded','addWatchlistToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addWatchlistToPage);

        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('createButton').addEventListener('click', this.createWatchlist);
        document.getElementById('clearButton').addEventListener('click', this.clearWatchlist);
        document.getElementById('deleteButton').addEventListener('click', this.deleteWatchlist);
        document.getElementById('searchButton').addEventListener('click', this.redirectToStockInfo);

        this.header.addHeaderToPage();
        this.client = new StockWatchListClient();
        this.clientLoaded();
    }

    async clientLoaded() {

        const watchlist = await this.client.getWatchLists();
        this.dataStore.set('watchlist', watchlist);

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
       this.lastAddedWatchlistName = watchlistName;

       const tbody = document.getElementById("watchlistTbody");
       const tr = document.createElement("tr");
       tr.innerHTML = `<td>${watchlistName}</td><td>${stockSymbols}</td>`;
       tbody.appendChild(tr);

        document.getElementById("watchlistInput").value = "";
        document.getElementById("stockSymbolsInput").value = "";
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

        const tbody = document.getElementById("watchlistTbody");
        const rows = tbody.getElementsByTagName("tr");
        for (let i = 0; i < rows.length; i++) {
                const cells = rows[i].getElementsByTagName("td");
                if (cells[0].textContent === watchlistName) {
                tbody.removeChild(rows[i]);
                break;
                 }
        }

        document.getElementById("watchlistInput").value ="";
    }

     addWatchlistToPage() {
        const watchlist = this.dataStore.get('watchlist');
        let stockSymbolsHtml = '';
        for (const el of watchlist) {
            stockSymbolsHtml += `
                <tr>
                    <td>${el.watchlistName}</td>
                    <td>${el.stockSymbols}</td>
                </tr>`;
        }
        document.getElementById('watchlistTbody').innerHTML = stockSymbolsHtml;
    }

     redirectToStockInfo(evt) {
            const stockSymbol = document.getElementById("stockSearchInput").value;
            if (stockSymbol.trim() === "") {
                alert("Please enter a stock symbol.");
                return;
            }

            window.location.href = `stockInfo.html?stockSymbol=${stockSymbol}`;
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


