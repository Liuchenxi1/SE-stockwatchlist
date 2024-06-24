Stock Watchlist
Overview

Having utilized various free analysis tools like Yahoo Finance and Robinhood, I've observed a common deficiency: they are not beginner-friendly. Rather than simplifying the process, these platforms either tend to be complex and demand significant professional knowledge or provide simple data. In my experience, these free available tools can overwhelm newcomers, leading to potential financial losses. I've personally faced such challenges and learned hard lessons. 

My aim is to bridge this gap and equip new investors with the insights and tools they need to navigate the financial world confidently. How can I reduce these confusions and provide valuable insights to encourage broader participation in finance?

This project is founded upon the premise of enabling beginners to make informed investment decisions. Upon completion, the application will empower users to select stocks and comprehend the associated risk factors, including the potential for price depreciation or appreciation.



Technologies
Frontend
JavaScript, HTML, CSS, Webpack, AXIOS

Backend
Java, AWS Lambda, DynamoDB

AlphaVantage API
AlphaVantage provides real-time and historical financial market data through a set of powerful and developer-friendly data APIs and spreadsheets.

Data Modeling
Query Model (DynamoDB)
- String username; (Primary Key)
- String queryId; (Sort Key)
- String dateRequested;
- String startDate;
- String endDate;
- String frequency;
- String symbol;
- String saved; 
- String title;
- String content;
Stock Model (AlphaVantageAPI)
AlphaVantages Free API provides the following key stock metrics:

Date
Open
Highs
Lows
Close
Volume
