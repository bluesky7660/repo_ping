var options = {
	chart: {
		height: 317,
		type: 'area',
		toolbar: {
			show: true,
			tools: {
			  zoom: true, 
			  zoomin: true, 
			  zoomout: true, 
			  reset: true, 
			  download: false,
			  pan: false,
			},
		},
	},
	dataLabels: {
		enabled: false
	},
	stroke: {
		curve: 'smooth',
		width: 3
	},
	series: [{
		name: '맵 포인트 생성',
		data: [1, 4, 5, 10, 2, 3, 2, 10, 3, 4, 6, 9]
	}, {
		name: '선상낚시 예약',
		data: [2, 8, 5, 7, 12, 2, 5, 5, 4, 10, 3, 6]
	}],
	grid: {
    borderColor: '#e0e6ed',
    strokeDashArray: 5,
    xaxis: {
      lines: {
        show: true
      }
    },   
    yaxis: {
      lines: {
        show: false,
      } 
    },
    padding: {
      top: 0,
      right: 0,
      bottom: 10,
      left: 0
    }, 
  },
	xaxis: {
		categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
	},
	yaxis: {
		labels: {
			show: false,
		}
	},
	colors: ['#4267cd', '#32b2fa'],
	markers: {
		size: 0,
		opacity: 0.1,
		colors: ['#4267cd', '#32b2fa'],
		strokeColor: "#ffffff",
		strokeWidth: 2,
		hover: {
			size: 7,
		}
	},
}

var chart = new ApexCharts(
	document.querySelector("#revenueGraph"),
	options
);

chart.render();