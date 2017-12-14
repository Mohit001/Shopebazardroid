package com.mohit.shopebazardroid.payu;


/**
 * Created by Rahul Hooda on 14/7/17.
 */
public enum AppEnvironment {

    SANDBOX {
        @Override
        public String merchant_Key() {
            return "LLKwG0";
        }

        @Override
        public String merchant_ID() {
            return "393463";
        }

        @Override
        public String furl() {
            return "https://www.payumoney.com/mobileapp/payumoney/failure.php";
        }

        @Override
        public String surl() {
            return "https://www.payumoney.com/mobileapp/payumoney/success.php";
        }

        @Override
        public String salt() {
            return "qauKbEAJ";
        }

        @Override
        public boolean debug() {
            return true;
        }
    },
    PRODUCTION {
        @Override
        /*public String merchant_Key() {
            return "O15vkB";
        }*/
        public String merchant_Key() {
            return "miIwQpXY";
        }

        @Override
        /*public String merchant_ID() {
            return "4819816";
        }*/
        public String merchant_ID() {
            return "5885607";
        }

        @Override
        public String furl() {
            return "https://www.payumoney.com/mobileapp/payumoney/failure.php";
        }

        @Override
        public String surl() {
            return "https://www.payumoney.com/mobileapp/payumoney/success.php";
        }

        @Override
        /*public String salt() {
            return "LU1EhObh";
        }*/
        public String salt() {
            return "uIKIHMxunF";
        }

        @Override
        public boolean debug() {
            return false;
        }
    };

    public abstract String merchant_Key();

    public abstract String merchant_ID();

    public abstract String furl();

    public abstract String surl();

    public abstract String salt();

    public abstract boolean debug();


}
