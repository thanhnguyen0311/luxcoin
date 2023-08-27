let container = document.getElementById('chart');
let chart = LightweightCharts.createChart(container, {
    rightPriceScale: {
        scaleMargins: {
            top: 0.2,
            bottom: 0.2,
        },
        borderVisible: false,
    },

    timeScale: {
        borderColor: "rgba(197, 203, 206, 0.8)",
        timeVisible: true,
        secondsVisible: true,
        rightBarStaysOnScroll: true,
    },
    layout: {
        background: {
            color: '#191c24'
        },
        textColor: 'white',
    },
    grid: {
        horzLines: {
            color: '#191c24',
        },
        vertLines: {
            color: '#191c24',
        },
    },
    crosshair: {
        vertLine: {
            labelVisible: true,
        },
    },
});

let series = chart.addAreaSeries({
    topColor: 'rgba(0, 150, 136, 0.56)',
    bottomColor: 'rgba(0, 150, 136, 0.04)',
    lineColor: 'rgba(0, 150, 136, 1)',
    lineWidth: 3,
    upColor : 'rgba(39, 204, 10, 0.8)',
    downColor : 'rgba(201, 44, 16, 0.8)'
});

let cdata
let intervalPrice1m
let intervalPrice

$(document).ready(function(){
    $("#time").change(function(event) {
        let timer = $(`#time`).val();
        clearInterval(intervalPrice1m);
        clearInterval(intervalPrice);
        chart.removeSeries(series)
        if(timer === "1m"){
            series = chart.addAreaSeries({
                topColor: 'rgba(0, 150, 136, 0.56)',
                bottomColor: 'rgba(0, 150, 136, 0.04)',
                lineColor: 'rgba(0, 150, 136, 1)',
                lineWidth: 3,
                upColor : 'rgba(39, 204, 10, 0.8)',
                downColor : 'rgba(201, 44, 16, 0.8)'
            });
            intervalPrice1m =  setInterval(PriceTimer1m, 1000);
        }
        else{
            series = chart.addCandlestickSeries();
            function PriceTimer(){
                fetch(`https://api.binance.com/api/v3/klines?symbol=BTCUSDT&interval=`+timer+`&limit=1000`)
                    .then(res => res.json())
                    .then(data => {
                        cdata = data.map(d => {
                            return {time:(d[0]/1000)+ 25200,open:parseFloat(d[1]),high:parseFloat(d[2]),low:parseFloat(d[3]),close:parseFloat(d[4])}
                        });
                        series.setData(cdata);
                    })
                    .catch(err => console.log(err))
            }
            intervalPrice =  setInterval(PriceTimer, 1000);
        }
    });
});
function PriceTimer1m(){

    fetch(`https://api.binance.com/api/v3/klines?symbol=BTCUSDT&interval=1s&limit=43200`)
        .then(res => res.json())
        .then(data => {
            cdata = data.map(d => {
                return {time:(d[0]/1000) +25200,value:parseFloat(d[4])}
            });
            series.setData(cdata);
        })
        .catch(err => console.log(err))
}


intervalPrice1m =  setInterval(PriceTimer1m, 1000);


function showTool(x) {
    toolTip.style.display = 'block';
}

function hideTool(x) {
    toolTip.style.display = 'none';
}


const toolTipWidth = 80;
const toolTipHeight = 80;
const toolTipMargin = 15;

// Create and style the tooltip html element
const toolTip = document.createElement('div');
toolTip.style = `width: 96px; height: 80px; position: absolute; display: block; padding: 8px; box-sizing: border-box; font-size: 12px; text-align: left; z-index: 1000; top: 12px; left: 12px; pointer-events: none; border: 1px solid; border-radius: 2px;font-family: -apple-system, BlinkMacSystemFont, 'Trebuchet MS', Roboto, Ubuntu, sans-serif; -webkit-font-smoothing: antialiased; -moz-osx-font-smoothing: grayscale;`;
toolTip.style.background = '#191c24';
toolTip.style.borderColor = '#191c24';
container.appendChild(toolTip);

// update tooltip
chart.subscribeCrosshairMove(param => {
    if (
        param.point === undefined ||
        !param.time ||
        param.point.x < 0 ||
        param.point.x > container.clientWidth ||
        param.point.y < 0 ||
        param.point.y > container.clientHeight
    ) {
        toolTip.style.display = 'none';
    } else {
        // time will be in the same format that we supplied to setData.
        // thus it will be YYYY-MM-DD
        const dateStr = timeConverter(param.time - 25200);
        toolTip.style.display = 'block';
        toolTip.style.backgroundColor = '#191c24'
        const data = param.seriesData.get(series);
        const price = data.value !== undefined ? data.value : data.close;
        toolTip.innerHTML = `<div style="color: ${'rgba(0, 150, 136, 1)'}">BTC/USDT</div><div style="font-size: 16px; margin: 4px 0px; ${'white'}">
			${Math.round(100 * price) / 100}
			</div><div style="color: ${'white'}">
			${dateStr}
			</div>`;

        const coordinate = series.priceToCoordinate(price);
        let shiftedCoordinate = param.point.x - 50;
        if (coordinate === null) {
            return;
        }
        shiftedCoordinate = Math.max(
            0,
            Math.min(container.clientWidth - toolTipWidth, shiftedCoordinate)
        );
        const coordinateY =
            coordinate - toolTipHeight - toolTipMargin > 0
                ? coordinate - toolTipHeight - toolTipMargin
                : Math.max(
                    0,
                    Math.min(
                        container.clientHeight - toolTipHeight - toolTipMargin,
                        coordinate + toolTipMargin
                    )
                );
        toolTip.style.left = shiftedCoordinate + 'px';
        toolTip.style.top = coordinateY + 'px';
    }
});


function timeConverter(UNIX_timestamp){
    let a = new Date(UNIX_timestamp * 1000);
    let months = ['Thg1','Thg2','Thg3','Thg4','Thg5','Thg6','Thg7','Thg8','Thg9','Thg10','Thg11','Thg12'];
    let year = a.getFullYear();
    let month = months[a.getMonth()];
    let date = a.getDate();
    let hour = a.getHours();
    let min = a.getMinutes();
    let sec = a.getSeconds();
    let time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;
    return time;
}