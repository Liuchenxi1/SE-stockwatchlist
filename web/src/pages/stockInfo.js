import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import StockWatchListClient from '../api/stockWatchListClient';

class StockInfo extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['mount', 'searchStock'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('searchButtom').addEventListener('click', this.searchInfo);
        document.getElementById('deleteButton').addEventListener('click', this.cleartheInput);
    }

    searchInfo(evt) {
        evt.preventDefault();

        if (StockInfo.trim() === "") {
            alert("Watchlist name cannot be empty");
            return;
        }

        const stock = document.getElementById("stockInfoInput").value;

        this.client.StockWatchListClient





    }

    cleartheInput(evt) {
        evt.preventDefault();
        document.getElementById("stockInfoInput").value = "";
    }

}

const mian = async () => {
    const searchInfo = new StockInfo();
    searchInfo.mount();
};

window.addEventListener('DOMContentLoaded', main);
