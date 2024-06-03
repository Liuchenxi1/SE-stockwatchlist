import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

class Index extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount','login','logout'], this);

        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        this.header.addHeaderToPage();
        }

    async login() {
        this.authenticator.login();
        window.location.href = "mainPage.html";
    }

    async logout() {
        await this.authenticator.logout();
    }
}


/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const index = new Index();
    index.mount();
};

window.addEventListener('DOMContentLoaded', main);