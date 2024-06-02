import BindingClass from "../util/bindingClass";

export default class Authenticator extends BindingClass {
    async login() {
        this.authenticator.login();
        window.location.href = "mainPage.html"
    }

    async logout() {
        await this.authenticator.logout();
        window.location.href = "index.html";
    }

}