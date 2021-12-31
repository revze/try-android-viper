# try-android-viper
The implementation of VIPER (View-Interactor-Presenter-Entity-Router) architecture in Android.

## How to Run

To make this project runs properly, you have to:

1. Clone [this API project](https://github.com/revze/menit.com-api) to your local server or cloud server.
2. Change ``BASE_URL`` config field in **build.gradle** to your local IP address or your domain name.
3. Change **domain** in **network_security_config.xml** to your local IP address or your domain name (if your server has not implement HTTPS).

To pass the login page you have to input username (```revan```) and password (```revan123```).

## Screenshot

<div style="display: flex">
    <img src="/previews/login.png" width="32%"/>
    <img src="/previews/welcome.png" width="32%"/>
</div>