#Instructions
1. From provided Bitbucket link, you can clone project to your pc
2. Either clean the libs (lazadatest\lib), then download selenium WebDriver Java language (http://docs.seleniumhq.org/download) and extract or
use existing libs that I has already imported.
3. Use Netbeans IDE to open project as java application. To install Netbeans IDE, you need to have JDK.
4. Clear existing libs and import new libs if you want to.
5. Has Firefox (can be version 33 or lower).
6. Build and run the application then see the result.

#Data assumtion
#1ST TEST
Hoten: "abc"
Email: "abc@gmail.com"
Matkhau: "abc123"
Error message: "Mật khẩu phải có ít nhất 1 chữ số"

Comments
- The homepage of Lazada site (http://www.lazada.vn/) sometimes open a ads popup during a time. This may cause
the test fails. I did a closePopup() function to handle it. Anyways, in order to handle the
unstable situations, I change url to faq site (http://www.lazada.vn/faq/) as you can see in the code.

#2ND TEST
product information to compare: http://www.lazada.vn/canon-eos-600d-18mp-den-ong-18-55mm-38844.html

Comments
- The js scrollIntoView is to handle the browser resolution situation. In my case, 'Mua ngay' button
is not clickable, it may not happen in your case.
- The result of comparison of delivery days is failed: there is no delivery time in cart page, so
you can decide whether it's a bug or not.

#3RD TEST

Comments
-No official solution for adding random product into cart
-Workaround: I can define to make a random selection of specified products. For ex:
A set of product url (as the url in 2nd test): product 1, product 2, ..., product 5.
Then I select 2 random ones to proceed further.
-I have not develop the test in workaround yet.

#Conclusions
- Tests run in sequence and doesn't run independently. 
For ex, the 1st test interrupts will make the 2nd test can not be run.
- Time loading page sometimes is long, it's a unstable running condition due to selenium and also the Lazada site implementations.
To reduce this may happen, I add implicit waits.
- I did a delay to delivery time.