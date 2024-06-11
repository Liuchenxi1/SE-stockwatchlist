import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

export default class StockWatchListClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createWatchlist','searchStockInfo'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
        clientLoaded() {
            if (this.props.hasOwnProperty("onReady")) {
                this.props.onReady(this);
            }
        }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
        
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }    

    async createWatchlist(watchlistName, stockSymbols, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create playlists.");
            const response = await this.axiosClient.post(`watchlists`, {
                watchlistName: watchlistName,
                stockSymbols: stockSymbols
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.watchList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async deleteWatchlist(watchlistName, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete watchlist.");
            const response = await this.axiosClient.delete(`watchlists`, {
                data: { watchlistName: watchlistName },
                headers: {
                Authorization: `Bearer ${token}`
            }
        });
        return response.data;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async searchStockInfo(stockSymbol, errorCallback) {
        try {
             const response = await this.axiosClient.get(`/stock/${stockSymbol}`);
             return response.data.stockInfoList;
        } catch (error) {
             console.error('Error fetching stock information:', error.message);
             return [];
        }
    }

    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }


}