import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

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

        // Optionally, add the header to the page
        this.header.addHeaderToPage();
    }

    createWatchlist(evt) {
        evt.preventDefault();

        const createInput = document.getElementById("createWatchlistInput").value;

        if (createInput.trim() === "") {
            alert("Watchlist name cannot be empty");
            return;
        }

        const li = document.createElement("li");
        li.textContent = createInput;

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


