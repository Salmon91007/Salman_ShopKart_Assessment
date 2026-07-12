package com.shopkart.ui.locators;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.$x;

public class XP {

    //Login Page Elements
    public static final String EMAIL = "//input[@name='email']";
    public static final String PASSWORD = "//input[@name='password']";
    public static final String SIGN_IN = "//button[@type='submit']";


    //Home page Elements
    public static final String PRODUCT = "//div[contains(@class,'product')][.//*[normalize-space()='%s']]";
    public static final String SEARCH_BOX = "//input[@id='catalog-search']";
    public static final String SEARCH_= "//button[@type='submit' and normalize-space()='Search']";
    public static final String PRODUCT_CARD = "//div[contains(@class,'product-card')][.//h2/button[normalize-space()='%s']]";
    public static final String ADD_TO_CART = PRODUCT+ "//button[normalize-space()='Add to cart']";

    //Header Elements
    public static final String CATALOG_BUTTON = "//nav[@aria-label='Primary navigation']//button[normalize-space()='Catalog']";
    public static final String CART_BUTTON = "//nav[@aria-label='Primary navigation']//button[normalize-space()='Cart']";
    public static final String API_DOCS_LINK = "//nav[@aria-label='Primary navigation']//a[normalize-space()='API docs']";
    public static final String LOGGED_IN_USER = "//nav[@aria-label='Primary navigation']//span[contains(@class,'signed-in')]";
    public static final String SIGN_OUT_BUTTON = "//button[@aria-label='Sign out']";


    //Cart Page Elements
    public static final String CART_HEADING = "//h1[normalize-space()='Your cart']";
    public static final String CART_CAPTION = "//caption[normalize-space()='Items selected for checkout']" ;
    public static final String CART_LINE = "//tr[contains(@class,'cart-line')][td[normalize-space()='%s']]";
    public static final String CART_PRODUCT = "//tr[contains(@class,'cart-line')][td[normalize-space()='%s']]";
    public static final String LINE_TOTAL = ".//td[contains(@class,'line-total') or position()=4]";
    public static final String CART_TOTAL ="//*[@data-role='cart-total']";
    public static final String  CONTINUE_SHOPPING = "//button[normalize-space()='Continue shopping']";
    public static final String CHECKOUT = "//button[normalize-space()='Checkout']";


    //Checkout Page Elements
    public static final String CHECKOUT_HEADING = "//h1[contains(text(),'Checkout')]";
    public static final String ADDRESS = "//textarea[@id='address']";
    public static final String PLACE_ORDER = "//button[@type='submit' and normalize-space()='Place order']";


    //Orders Page Elements
    public static final String ORDER_NUMBER = "//section[contains(@class,'order-confirmation')]//p";
    public static final String ORDER_STATUS = "//dd[@data-field='order-status']";
    public static final String ORDER_TOTAL = "//dd[@data-field='order-total']";
    public static final String DELIVERY_ADDRESS = "//dt[normalize-space()='Delivery address']/following-sibling::dd";
    public static final String RETURN_TO_CATALOG = "//button[normalize-space()='Return to catalog']";




    public static SelenideElement product(String name)
    { return $x(String.format(PRODUCT, name));
    }

    public static SelenideElement addToCart(String name)
    { return $x(String.format(ADD_TO_CART, name));
    }

    public static SelenideElement cartLine(String sku)
    { return $x(String.format(CART_LINE, sku));
    }

}