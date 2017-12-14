package com.mohit.shopebazardroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.model.request.CartItemRequest;
import com.mohit.shopebazardroid.model.request.CustomOptionRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;

/**
 * Created by root on 8/7/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    //---------- database information -----------
        public static final String DATABASE_NAME = "ecommerce.db";
        public static final Integer DATABASE_VARSION = 1;

    //--------- all tables ------------------
        private final String CART_TABLE = "cart";
        private final String CUSTOM_OPTION_TABLE = "custom_option_table";

    //--------- common field--------------
        private final String CUSTOMER_ID = "customer_id";
    //--------- cart table field ---------------

        private final String CART_ID = "cart_id";
        private final String CART_PRODUCT_ID = "product_id";
        private final String CART_PRODUCT_NAME = "product_name";
        private final String CART_PRODUCT_SKU = "product_sku";
        private final String CART_PRODUCT_QTY = "product_qty";
        private final String CART_HAS_CUSTOM_OPTION = "has_custom_option";

    //--------- custom option table field ---------------

        private final String CUSTOM_OPTION_ID = "custom_option_id";
        private final String CART_PK = "cart_pk";
        private final String CUSTOM_TITLE_ID = "custom_title_id";
        private final String CUSTOM_TITLE = "custom_title";
        private final String CUSTOM_TITLE_VALUE_ID = "custom_title_value_id";
        private final String CUSTOM_TITLE_VALUE = "custom_title_value";

    //----------- create table query -------------
        private String CREATE_CART_TABLE_QUERY="create table "+CART_TABLE+
            " ("+CART_ID+" integer primary key,"+CUSTOMER_ID+" integer, "+CART_PRODUCT_ID+" text,"+CART_PRODUCT_NAME+" text,"+CART_PRODUCT_SKU+" text,"+CART_PRODUCT_QTY+" integer,"+CART_HAS_CUSTOM_OPTION+" integer)";
        private String CREATE_CUSTOM_OPTION_TABLE_QUERY="create table "+CUSTOM_OPTION_TABLE+
            " ("+CUSTOM_OPTION_ID+" integer primary key,"+CUSTOMER_ID+" integer, "+CART_PK+" integer, "+CART_PRODUCT_ID+" integer,"+CUSTOM_TITLE_ID+" integer,"+CUSTOM_TITLE+" text,"+CUSTOM_TITLE_VALUE_ID+" integer,"+CUSTOM_TITLE_VALUE+" text)";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VARSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        //---------- execute create all table ---------
            db.execSQL(CREATE_CART_TABLE_QUERY);// cart
            db.execSQL(CREATE_CUSTOM_OPTION_TABLE_QUERY);//custom option
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        //db.execSQL("DROP TABLE IF EXISTS contacts");
        //onCreate(db);
    }

    public String insertIntoCart(CartItemRequest cartItemRequest)
    {
        String message=null;
        boolean checkAvailability=false;
        //--------- check product availability----------------

        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor;
        if(cartItemRequest.getHas_custom_option() == 1)
        {
            String inString;
            ArrayList<Object> inStringArray=new ArrayList<>();
            for (int i = 0; i < cartItemRequest.getCustomOptions().size(); i++) {
                CustomOptionRequest customOptionRequest = cartItemRequest.getCustomOptions().get(i);
                inStringArray.add(""+customOptionRequest.getCustom_title_value_id());
            }
            inString = Utility.ArraytoStringForInOperator(inStringArray);
            String checkProductCustomOptionExistQuery= "select * from "+CUSTOM_OPTION_TABLE+" where "+CUSTOM_TITLE_VALUE_ID+" IN "+inString+" and "+CART_PRODUCT_ID+"="+cartItemRequest.getProductId()+" and "+CUSTOMER_ID+"="+ MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID,"0");
            cursor =  readableDatabase.rawQuery(checkProductCustomOptionExistQuery, null );
            if(cursor.getCount() > 0)
            {
                checkAvailability =true;
                if(cursor.moveToFirst()) {
                    int cartId = cursor.getInt(cursor.getColumnIndex(CART_PK));
                    //--------- get qty -----------
                    String getQtyQuery= "select * from "+CART_TABLE+" where "+CART_ID+"="+cartId+" and "+CUSTOMER_ID+"="+ MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID,"0");
                    cursor =  readableDatabase.rawQuery(getQtyQuery, null );
                    if(cursor.getCount()>0)
                    {
                        int Qty = 0;
                        if (cursor.moveToFirst()) {
                            Qty = cursor.getInt(cursor.getColumnIndex(CART_PRODUCT_QTY));
                        }
                        Qty = Qty + 1;
                        //-------- update qty ------------
                        updateQtyFromCart(cartId, Qty);
                    }
                }

            }
        }
        else
        {
            String checkProductExistQuery = "select * from "+CART_TABLE+" where "+CART_PRODUCT_ID+"="+cartItemRequest.getProductId()+" and "+CART_HAS_CUSTOM_OPTION+"= 0"+" and "+CUSTOMER_ID+"="+ MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID,"0");
            cursor =  readableDatabase.rawQuery(checkProductExistQuery, null );
            if(cursor.getCount() > 0)
            {
                checkAvailability =true;
                int Qty = 0;
                int cartId = 0;
                if (cursor.moveToFirst()) {
                    cartId = cursor.getInt(cursor.getColumnIndex(CART_ID));//get cart id
                    Qty = cursor.getInt(cursor.getColumnIndex(CART_PRODUCT_QTY));//get qty
                }
                Qty = Qty + 1;
                //-------- update qty ------------
                updateQtyFromCart(cartId, Qty);
            }
        }

        if(!checkAvailability)
        {
            //------------ insert cart item --------------

            SQLiteDatabase writableDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(CART_PRODUCT_ID, cartItemRequest.getProductId());
            contentValues.put(CART_PRODUCT_NAME, cartItemRequest.getProductName());
            contentValues.put(CART_PRODUCT_SKU, cartItemRequest.getProductSku());
            contentValues.put(CART_PRODUCT_QTY, cartItemRequest.getProductQty());
            contentValues.put(CART_HAS_CUSTOM_OPTION, cartItemRequest.getHas_custom_option());
            long id = writableDatabase.insert(CART_TABLE, null, contentValues);

            for (int i = 0; i < cartItemRequest.getCustomOptions().size(); i++) {
                CustomOptionRequest customOptionRequest = cartItemRequest.getCustomOptions().get(i);
                contentValues = new ContentValues();
                contentValues.put(CART_PK, (int) id);
                contentValues.put(CART_PRODUCT_ID, cartItemRequest.getProductId());
                contentValues.put(CUSTOM_TITLE_ID, customOptionRequest.getCustom_title_id());
                contentValues.put(CUSTOM_TITLE, customOptionRequest.getCustom_title());
                contentValues.put(CUSTOM_TITLE_VALUE_ID, customOptionRequest.getCustom_title_value_id());
                contentValues.put(CUSTOM_TITLE_VALUE, customOptionRequest.getCustom_title_value());
                writableDatabase.insert(CUSTOM_OPTION_TABLE, null, contentValues);
            }
            if(id >0)
            {
                message = "Successfully,Added Product Into Cart.";
            }
        }
        else
        {
            message = "Successfully,Updated Product Qty Cart.";
        }

        return message;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CART_TABLE);
        return numRows;
    }

    public boolean updateQtyFromCart (int id,int qty)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CART_PRODUCT_QTY, qty);
        db.update(CART_TABLE, contentValues, CART_ID+" = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteItemFromCart(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CART_TABLE,
                CART_ID+" = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<CartItemRequest> getAllCartItems()
    {

        //---------- get value query -----------
        String getCartItemsQuery = "select * from "+CART_TABLE+" where "+CUSTOMER_ID+"="+ MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID,"0");

        ArrayList<CartItemRequest> array_list = new ArrayList<CartItemRequest>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(getCartItemsQuery, null );
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false)
        {
            CartItemRequest cartItemRequest=new CartItemRequest();
            int cartId=cursor.getInt(cursor.getColumnIndex(CART_ID));
            cartItemRequest.setCartId(cartId);
            cartItemRequest.setProductId(cursor.getString(cursor.getColumnIndex(CART_PRODUCT_ID)));
            cartItemRequest.setProductName(cursor.getString(cursor.getColumnIndex(CART_PRODUCT_NAME)));
            cartItemRequest.setProductSku(cursor.getString(cursor.getColumnIndex(CART_PRODUCT_SKU)));
            cartItemRequest.setProductQty(cursor.getInt(cursor.getColumnIndex(CART_PRODUCT_QTY)));
            cartItemRequest.setHas_custom_option(cursor.getInt(cursor.getColumnIndex(CART_HAS_CUSTOM_OPTION)));
            //-------- for custom option value ---------
            if(cursor.getInt(cursor.getColumnIndex(CART_HAS_CUSTOM_OPTION)) == 1)
            {
                String getCustomOptionItemQuery = "select * from "+CUSTOM_OPTION_TABLE+" where "+CART_ID+"="+cartId;
                Cursor custom_option_cursor =  db.rawQuery(getCartItemsQuery, null );
                custom_option_cursor.moveToFirst();
                ArrayList<CustomOptionRequest> customOptionArray=new ArrayList<>();
                while(custom_option_cursor.isAfterLast() == false)
                {
                    CustomOptionRequest customOptionRequest=new CustomOptionRequest();
                    customOptionRequest.setCustom_title_id(custom_option_cursor.getInt(custom_option_cursor.getColumnIndex(CUSTOM_TITLE_ID)));
                    customOptionRequest.setCustom_title(custom_option_cursor.getString(custom_option_cursor.getColumnIndex(CUSTOM_TITLE)));
                    customOptionRequest.setCustom_title_value_id(custom_option_cursor.getInt(custom_option_cursor.getColumnIndex(CUSTOM_TITLE_VALUE_ID)));
                    customOptionRequest.setCustom_title_value(custom_option_cursor.getString(custom_option_cursor.getColumnIndex(CUSTOM_TITLE_VALUE)));
                    customOptionArray.add(customOptionRequest);
                    custom_option_cursor.moveToNext();
                }
                cartItemRequest.setCustomOptions(customOptionArray);
            }

            array_list.add(cartItemRequest);
            cursor.moveToNext();
        }
        return array_list;
    }
}
