# SalesTaxes

[![Build Status](https://travis-ci.com/DanielDeGaspari/SalesTaxes.svg?token=Kfq4sNXTkytYEmiJRzyy&branch=master)](https://travis-ci.com/DanielDeGaspari/SalesTaxes)

# Getting started with Sales Taxes 🚀

[![Sales Taxes](https://nebraska.tv/resources/media/cff414fc-4e0b-4489-ad96-4e4e33e6f030-large16x9_SalesTax.jpg?1548982237772)](https://github.com/DanielDeGaspari/SalesTaxes)

## Requirements 🔧

In order to continue, the following requirements must be satisfied:

- **JDK** 1.8 or greater
- **Apache Maven**

---

## Sales taxes problem:

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.
 
When I purchase items I receive a receipt which lists the name of all the items and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.
 
Write an application that prints out the receipt details for these shopping baskets...
 
INPUT:

---
 
```
Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85
```
---
```
Input 2:
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50
```
---
```
Input 3:
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25
```
---

OUTPUT:

---
```
Output 1:
1 book : 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83
```
---
```
Output 2:
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15
```
---
```
Output 3:
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68
```
---

## Usage

### Build

First of all, you have to build the application and you can do that with **Apache Maven**m with the following command:

`mvn clean install`

### Run

You can run the application represented by sales-taxes-calculator.jar file, that you can find under "target" generated folder.

Usage:

`java -jar target/sales-taxes-calculator.jar filename(s)`

Example:

`java -jar target/sales-taxes-calculator.jar samples/basket1.txt`

You can find some example of basket under "samples" folder, named "basket1.txt", "basket2.txt", "basket3.txt".

### Output

Once application has generated the receipt, you can find it under "samples" folder, with the following naming convetion:
`Purchase[file_number]Generated.txt` where file_number is the index of the file given in input

example:

`java -jar target/sales-taxes-calculator.jar samples/basket1.txt samples/basket2.txt`

Output:

```
samples/Purchase1Generated.txt -> samples/basket1.txt
samples/Purchase2Generated.txt -> samples/basket2.txt
```

## Unit tests ✅

It is possible to perform unit tests running following command in project's base folder. I made them using `JUnit`. To run tests:

`mvn test`

### Samples

You can find the 3 samples written in section "Sales taxes problem" coded as tests in the following file:
 
 `./src/main/test/java/io/github/salestaxes/ContextTest.java`

---

## Thank you for your attention 👍

[![Thank you](https://www.humorside.com/wp-content/uploads/2017/12/thank-you-meme-28.jpg)](http://serverless.com)
