<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>
<html>
<head>
    <title>${companyName}-${companyCode}</title>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="price" style="height:400px"></div>
<div id="rate" style="height:400px"></div>
<div id="money" style="height:400px"></div>

<!-- ECharts单文件引入 -->
<script src="${ctx}assets/js/echart/echarts.js" type="text/javascript"></script>

<%--资金流入--%>
<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: '${ctx}assets/js/echart/'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var money = ec.init(document.getElementById('money'));
            option = {
                title : {
                    text: '流入趋势-${date}',
                    subtext: '${companyName}-${companyCode}'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['${date}-流入']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data:${times}
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'${date}-流入',
                        type:'line',
                        smooth:true,
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data:${moneys}
                    }
                ]
            };
            // 为echarts对象加载数据
            money.setOption(option);
        }
    );
</script>

<%--涨幅趋势--%>
<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: '${ctx}assets/js/echart/'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/line', // 使用柱状图就加载bar模块，按需加载
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var rate = ec.init(document.getElementById('rate'));
            option = {
                title : {
                    text: '涨幅趋势-${date}',
                    subtext: '${companyName}-${companyCode}'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['${date}-涨幅']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : ${times}
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'${date}-涨幅',
                        type:'line',
                        data:${rates},
                        itemStyle:{
                            normal:{
                                label:{show:true}
                            }
                        }
                    }

                ]
            };

            // 为echarts对象加载数据
            rate.setOption(option);
        }
    );
</script>

<%--价格趋势--%>
<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: '${ctx}assets/js/echart/'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var price = ec.init(document.getElementById('price'));
            option = {
                title : {
                    text: '价格趋势-${date}',
                    subtext: '${companyName}-${companyCode}'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['${date}-价格']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : ${times}
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        min : ${minPrice},
                        max : ${maxPrice}
                    }
                ],
                series : [
                    {
                        name:'${date}-价格',
                        type:'line',
                        data:${prices},
                        itemStyle:{
                            normal:{
                                label:{show:true}
                            }
                        }
                    }

                ]
            };

            // 为echarts对象加载数据
            price.setOption(option);
        }
    );
</script>

</body>
</html>
