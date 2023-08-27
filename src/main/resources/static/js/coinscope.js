let ws = new WebSocket('wss://stream.binance.com:9443/ws/btcusdt@trade');
let btcPriceShow = document.getElementById("btc-price");
let lastBTCprice = null;

let btc24hChange = document.getElementById("btc-24hChange");
let btcIcon = document.getElementById("btc-icon");
let btcIconChild = document.getElementById("btc-icon-child");


let btcPrice = null;

ws.onmessage = (event) => {
    let stockObject = JSON.parse(event.data);
    btcPrice = parseFloat(stockObject.p).toFixed(2);
}
async function btcPriceTimer() {
    btcPriceShow.innerText = btcPrice;
    btcPriceShow.style.color = !lastBTCprice || lastBTCprice === btcPrice ? 'white' : btcPrice > lastBTCprice ? '#00cc00' : 'red';
    lastBTCprice = btcPrice;

    let btc24hChangeResponse = await fetch('https://api.binance.com/api/v3/ticker/24hr?symbol=BTCUSDT');
    let btc24hInfo = await btc24hChangeResponse.json();
    let btc24hPriceChange = parseFloat(btc24hInfo.priceChangePercent).toFixed(2);
    btc24hChange.innerText = btc24hPriceChange + "%";
    if (btc24hPriceChange > 0) {
        if(btc24hChange.classList.contains('text-danger')) {
            btc24hChange.classList.add('text-success');
            btc24hChange.classList.remove('text-danger');
        }
        if(!btc24hChange.classList.contains('text-success')){
            btc24hChange.classList.add('text-success');
        }
        if(btcIcon.classList.contains('icon-box-danger')){
            btcIcon.classList.add('icon-box-success');
            btcIconChild.classList.add('mdi-arrow-top-right');
            btcIconChild.classList.remove('mdi-arrow-bottom-left');
            btcIcon.classList.remove('icon-box-danger');
        }
        if(!btcIcon.classList.contains('icon-box-success')){
            btcIcon.classList.add('icon-box-success');
            btcIconChild.classList.add('mdi-arrow-top-right');
        }
    }
    else{
        if(btc24hChange.classList.contains('text-success')) {
            btc24hChange.classList.add('text-danger');
            btc24hChange.classList.remove('text-success');
            return;
        }
        if(!btc24hChange.classList.contains('text-danger')){
            btc24hChange.classList.add('text-danger');
        }
        if(btcIcon.classList.contains('icon-box-success')){
            btcIcon.classList.add('icon-box-danger');
            btcIconChild.classList.add('mdi-arrow-bottom-left');
            btcIconChild.classList.remove('mdi-arrow-top-right');
            btcIcon.classList.remove('icon-box-success');
        }
        if(!btcIcon.classList.contains('icon-box-danger')){
            btcIconChild.classList.add('mdi-arrow-bottom-left');
            btcIcon.classList.add('icon-box-danger');
        }
    }
}
setInterval(btcPriceTimer, 1000);

let wsBNB = new WebSocket('wss://stream.binance.com:9443/ws/bnbusdt@trade');
let bnbPriceShow = document.getElementById("bnb-price");
let lastBNBprice = null;

let bnb24hChange = document.getElementById("bnb-24hChange");
let bnbIcon = document.getElementById("bnb-icon");
let bnbIconChild = document.getElementById("bnb-icon-child");


let bnbPrice = null;

wsBNB.onmessage = (event) => {
    let stockObject = JSON.parse(event.data);
    bnbPrice = parseFloat(stockObject.p).toFixed(2);
}
async function bnbPriceTimer() {
    bnbPriceShow.innerText = bnbPrice;
    bnbPriceShow.style.color = !lastBNBprice || lastBNBprice === bnbPrice ? 'white' : bnbPrice > lastBNBprice ? '#00cc00' : 'red';
    lastBNBprice = bnbPrice;

    let bnb24hChangeResponse = await fetch('https://api.binance.com/api/v3/ticker/24hr?symbol=BNBUSDT');
    let bnb24hInfo = await bnb24hChangeResponse.json();
    let bnb24hPriceChange = parseFloat(bnb24hInfo.priceChangePercent).toFixed(2);
    bnb24hChange.innerText = bnb24hPriceChange + "%";
    if (bnb24hPriceChange > 0) {
        if(bnb24hChange.classList.contains('text-danger')) {
            bnb24hChange.classList.add('text-success');
            bnb24hChange.classList.remove('text-danger');
        }
        if(!bnb24hChange.classList.contains('text-success')){
            bnb24hChange.classList.add('text-success');
        }
        if(bnbIcon.classList.contains('icon-box-danger')){
            bnbIcon.classList.add('icon-box-success');
            bnbIconChild.classList.add('mdi-arrow-top-right');
            bnbIconChild.classList.remove('mdi-arrow-bottom-left');
            bnbIcon.classList.remove('icon-box-danger');
        }
        if(!bnbIcon.classList.contains('icon-box-success')){
            bnbIcon.classList.add('icon-box-success');
            bnbIconChild.classList.add('mdi-arrow-top-right');
        }
    }
    else{
        if(bnb24hChange.classList.contains('text-success')) {
            bnb24hChange.classList.add('text-danger');
            bnb24hChange.classList.remove('text-success');
            return;
        }
        if(!bnb24hChange.classList.contains('text-danger')){
            bnb24hChange.classList.add('text-danger');
        }
        if(bnbIcon.classList.contains('icon-box-success')){
            bnbIcon.classList.add('icon-box-danger');
            bnbIconChild.classList.add('mdi-arrow-bottom-left');
            bnbIconChild.classList.remove('mdi-arrow-top-right');
            bnbIcon.classList.remove('icon-box-success');
        }
        if(!bnbIcon.classList.contains('icon-box-danger')){
            bnbIconChild.classList.add('mdi-arrow-bottom-left');
            bnbIcon.classList.add('icon-box-danger');
        }
    }
}
setInterval(bnbPriceTimer, 1000);




let wsETH = new WebSocket('wss://stream.binance.com:9443/ws/ethusdt@trade');
let ethPriceShow = document.getElementById("eth-price");
let lastETHprice = null;

let eth24hChange = document.getElementById("eth-24hChange");
let ethIcon = document.getElementById("eth-icon");
let ethIconChild = document.getElementById("eth-icon-child");

let ethPrice = null;

wsETH.onmessage = (event) => {
    let stockObject = JSON.parse(event.data);
    ethPrice = parseFloat(stockObject.p).toFixed(2);
}
async function ethPriceTimer() {
    ethPriceShow.innerText = ethPrice;
    ethPriceShow.style.color = !lastETHprice || lastETHprice === ethPrice ? 'white' : ethPrice > lastETHprice ? '#00cc00' : 'red';
    lastETHprice = ethPrice;

    let eth24hChangeResponse = await fetch('https://api.binance.com/api/v3/ticker/24hr?symbol=ETHUSDT');
    let eth24hInfo = await eth24hChangeResponse.json();
    let eth24hPriceChange = parseFloat(eth24hInfo.priceChangePercent).toFixed(2);
    eth24hChange.innerText = eth24hPriceChange + "%";
    if (eth24hPriceChange > 0) {
        if(eth24hChange.classList.contains('text-danger')) {
            eth24hChange.classList.add('text-success');
            eth24hChange.classList.remove('text-danger');
        }
        if(!eth24hChange.classList.contains('text-success')){
            eth24hChange.classList.add('text-success');
        }
        if(ethIcon.classList.contains('icon-box-danger')){
            ethIcon.classList.add('icon-box-success');
            ethIconChild.classList.add('mdi-arrow-top-right');
            ethIconChild.classList.remove('mdi-arrow-bottom-left');
            ethIcon.classList.remove('icon-box-danger');
        }
        if(!ethIcon.classList.contains('icon-box-success')){
            ethIcon.classList.add('icon-box-success');
            ethIconChild.classList.add('mdi-arrow-top-right');
        }
    }
    else{
        if(eth24hChange.classList.contains('text-success')) {
            eth24hChange.classList.add('text-danger');
            eth24hChange.classList.remove('text-success');
            return;
        }
        if(!eth24hChange.classList.contains('text-danger')){
            eth24hChange.classList.add('text-danger');
        }
        if(ethIcon.classList.contains('icon-box-success')){
            ethIcon.classList.add('icon-box-danger');
            ethIconChild.classList.add('mdi-arrow-bottom-left');
            ethIconChild.classList.remove('mdi-arrow-top-right');
            ethIcon.classList.remove('icon-box-success');
        }
        if(!ethIcon.classList.contains('icon-box-danger')){
            ethIconChild.classList.add('mdi-arrow-bottom-left');
            ethIcon.classList.add('icon-box-danger');
        }
    }
}
setInterval(ethPriceTimer, 1000);






let wsDOGE = new WebSocket('wss://stream.binance.com:9443/ws/dogeusdt@trade');
let dogePriceShow = document.getElementById("doge-price");
let lastDOGEprice = null;

let doge24hChange = document.getElementById("doge-24hChange");
let dogeIcon = document.getElementById("doge-icon");
let dogeIconChild = document.getElementById("doge-icon-child");

let dogePrice = null;

wsDOGE.onmessage = (event) => {
    let stockObject = JSON.parse(event.data);
    dogePrice = parseFloat(stockObject.p).toFixed(5);
}
async function dogePriceTimer() {
    dogePriceShow.innerText = dogePrice;
    dogePriceShow.style.color = !lastDOGEprice || lastDOGEprice === dogePrice ? 'white' : dogePrice > lastDOGEprice ? '#00cc00' : 'red';
    lastDOGEprice = dogePrice;

    let doge24hChangeResponse = await fetch('https://api.binance.com/api/v3/ticker/24hr?symbol=DOGEUSDT');
    let doge24hInfo = await doge24hChangeResponse.json();
    let doge24hPriceChange = parseFloat(doge24hInfo.priceChangePercent).toFixed(2);
    doge24hChange.innerText = doge24hPriceChange + "%";
    if (doge24hPriceChange > 0) {
        if(doge24hChange.classList.contains('text-danger')) {
            doge24hChange.classList.add('text-success');
            doge24hChange.classList.remove('text-danger');
        }
        if(!doge24hChange.classList.contains('text-success')){
            doge24hChange.classList.add('text-success');
        }
        if(dogeIcon.classList.contains('icon-box-danger')){
            dogeIcon.classList.add('icon-box-success');
            dogeIconChild.classList.add('mdi-arrow-top-right');
            dogeIconChild.classList.remove('mdi-arrow-bottom-left');
            dogeIcon.classList.remove('icon-box-danger');
        }
        if(!dogeIcon.classList.contains('icon-box-success')){
            dogeIcon.classList.add('icon-box-success');
            dogeIconChild.classList.add('mdi-arrow-top-right');
        }
    }
    else{
        if(doge24hChange.classList.contains('text-success')) {
            doge24hChange.classList.add('text-danger');
            doge24hChange.classList.remove('text-success');
            return;
        }
        if(!doge24hChange.classList.contains('text-danger')){
            doge24hChange.classList.add('text-danger');
        }
        if(dogeIcon.classList.contains('icon-box-success')){
            dogeIcon.classList.add('icon-box-danger');
            dogeIconChild.classList.add('mdi-arrow-bottom-left');
            dogeIconChild.classList.remove('mdi-arrow-top-right');
            dogeIcon.classList.remove('icon-box-success');
        }
        if(!dogeIcon.classList.contains('icon-box-danger')){
            dogeIconChild.classList.add('mdi-arrow-bottom-left');
            dogeIcon.classList.add('icon-box-danger');
        }
    }
}
setInterval(dogePriceTimer, 1000);