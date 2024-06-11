import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import StockWatchListClient from '../api/stockWatchListClient';

class StockInfo extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['mount', 'generateStockInfoTable', 'redirectToMainPage', 'loadStockInfo'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        this.header.addHeaderToPage();
        this.redirectToMainPage();

        const stockSymbol = localStorage.getItem('stockSymbol');
    }

    generateStockInfoTable(stockInfoList) {
        if (stockInfoList.length === 0) {
            return "<p>No stock information available.</p>";
        }
        let tableHTML = "<table>";
        tableHTML += "<tr><th>Date</th><th>Open</th><th>Close</th><th>Low</th><th>High</th><th>Volume</th></tr>";
        stockInfoList.forEach(stock => {
            tableHTML += `<tr><td>${stock.timestamp}</td><td>${stock.open}</td><td>${stock.close}</td><td>${stock.low}</td><td>${stock.high}</td><td>${stock.volume}</td></tr>`;
        });
        tableHTML += "</table>";
        return tableHTML;
    }

    redirectToMainPage() {
        document.getElementById("mainPageButton").addEventListener("click", function() {
            window.location.href = "mainPage.html";
        });
    }

    async loadStockInfo(stockSymbol) {
            try {
                const stockInfoList = await this.client.searchStockInfo(stockSymbol,errorCallback);
                const stockInfoTableHTML = this.generateStockInfoTable(stockInfoList);
                document.getElementById('stock-info-table').innerHTML = stockInfoTableHTML;
            } catch (error) {
                console.error("Failed to load stock information", error);
                document.getElementById('stock-info-table').innerHTML = "<p>Failed to load stock information.</p>";
            }
    }

}

const main = async () => {
    const searchInfo = new StockInfo();
    await searchInfo.mount();
};

window.addEventListener('DOMContentLoaded', main);


