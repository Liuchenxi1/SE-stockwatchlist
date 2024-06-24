# Stock Watchlist
## Overview
Having utilized various free analysis tools like Yahoo Finance and Robinhood, I've observed a common deficiency: they are not beginner-friendly. Rather than simplifying the process, these platforms either tend to be complex and demand significant professional knowledge or provide simple data. In my experience, these free available tools can overwhelm newcomers, leading to potential financial losses. I've personally faced such challenges and learned hard lessons. My aim is to bridge this gap and equip new investors with the insights and tools they need to navigate the financial world confidently. How can I reduce these confusions and provide valuable insights to encourage broader participation in finance? This project is founded upon the premise of enabling beginners to make informed investment decisions. Upon completion, the application will empower users to select stocks and comprehend the associated risk factors, including the potential for price depreciation or appreciation.

## Technologies
Frontend
JavaScript, HTML, CSS, Webpack, AXIOS

Backend
Java, AWS Lambda, DynamoDB

Yahoo Finance API
Yahoo Finance API provides real-time and historical financial market data. 

### Data Modeling
WatchlistModel (DynamoDB)

String // userEmail
String // Watchlist Name
List<String> // StockSymbols  

Stock Model (Yahoo Finance API)
API provides the following key stock metrics:
String // Stock Name;
Double // 52wk high;
Double // 52wk low;
Double // Today open price;
Double // Today close price;
Integer // volume

## User cases
1.As a user, I want to login in my watchlists
2.As a user, I want to check individual stock information
3.As a user, I want to create stock watchlist
4.As a user, I want to add individual stock to the watchlist
5.As a user, I am able to remove individual stock from watchlist when I want to 
6.As a user, I am able to create multiple watchlists with different names
7.As a user, I am able to remove a watchlist that if I didn’t need it anymore
8.As a user, I want to see analysis for this stock based on the data (Stretch goal)


