<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>data-my-center</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}assets/css/common/bootstrap.min.css">
    <style>
        td {
            padding: 6px 5px;
            background-color: #fff;
            border: 1px solid #ddd;
            min-width: 10px;
            text-align: center;
        }

        th {
            border: 1px solid #ddd;
            height: 45px;
            background: azure;
            padding: 8px 6px;
            text-align: center;
        }

        input {
            height: 30px;
            padding: 3px 5px;
            margin: 3px 5px;
            min-width: 100px;
        }

        select {
            height: 30px;
            padding: 3px 5px;
            margin: 3px 5px;
            min-width: 100px;
        }
    </style>
</head>
<body>


<form action="/day/index" method="get">
    <div id="rate" style="height:400px"></div>
    <div id="kdj" style="height:400px"></div>
    <div id="ddm" style="height:400px"></div>
    <div id="inflow" style="height:400px"></div>

    <p style="color: red">01.dea在上不能买</p>
    <p style="color: red">02.买完之后连着两天下跌，处理掉</p>
    <p style="color: red">03.第二天 或者第三天 出现跌幅出现2% 卖掉</p>
    <p style="color: red">04 第二第三天跌幅累加总和 超过2% 坚决卖掉</p>


    <div style="margin:0 auto; width: 90%;text-align: center;">
        <table style="width: 100%;">
            <tbody>
            <tr>
                <th>index</th>
                <th>name-code</th>
                <th>inflow</th>
                <th>volume</th>
                <th>amount</th>
                <th>start</th>
                <th>end</th>
                <th>max</th>
                <th>min</th>
                <th>rate</th>
                <th>diff</th>
                <th>dea</th>
                <th>macd</th>
                <th>dem-r</th>
                <th>k</th>
                <th>d</th>
                <th>j</th>
                <th>kdj-r</th>
                <th>date</th>
            </tr>
    <c:forEach items="${dayValues}" var="itemNext" varStatus="var">
        <c:if test="${var.count>1}">
        <%--item 前，itemOld后--%>
            <c:forEach items="${dayValues}" var="item" begin="${var.count-2}" end="${var.count-2}" step="1" >
            <tr>
                    <%--当前--%>
                <td>${var.count-1}</td>
                    <%--公司名称-编码--%>
                <td>
                    <a href="/data/chart?companyCode=${company.code}&date=${item.date}" target="_blank"> ${company.name}</a> -
                    <c:choose>
                        <c:when test="${ fn:substring(company.code ,0,3)=='600' or fn:substring(company.code ,0,2)=='60'}">
                            <a href="http://quote.eastmoney.com/concept/sh${company.code}.html?from=classic&eventcode=Web_quote_entrance2"
                               target="_blank">${company.code}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="http://quote.eastmoney.com/concept/sz${company.code}.html?from=classic&eventcode=Web_quote_entrance2"
                               target="_blank">${company.code}</a>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td style="
                    <c:choose>
                <c:when test="${item.totalMoney>0 && item.totalMoney>itemNext.totalMoney}">
                        color:red;
                        </c:when>
                <c:when test="${item.totalMoney<-70}">
                        color:green;
                </c:when>
                        </c:choose>">${item.totalMoney}</td>
                <td style="
                <c:choose>
                <c:when test="${item.volume/10000<2000}">
                        color:green;
                </c:when>
                        </c:choose>"> <fmt:formatNumber type="number" value="${item.volume/10000}" pattern="0.0" maxFractionDigits="1"/> </td>
                <td style="
                <c:choose>
                <c:when test="${item.amount/10000>60000 }">
                        color:red;
                </c:when>
                <c:when test="${item.amount/10000<2000 }">
                        color:green;
                </c:when>

                        </c:choose>"><fmt:formatNumber type="number" value="${ item.amount/10000}" pattern="0.0" maxFractionDigits="1"/></td>
                    <%--半小时之前的涨幅--%>
                <td>${item.startPrice}</td>
                    <%--一小时之前的涨幅--%>
                <td>${item.endPrice}</td>
                    <%--当前净流入--%>
                <td>${item.maxPrice}</td>
                <td>${item.minPrice}</td>
                    <%--涨跌幅度--%>
                <td style="
                <c:choose>
                <c:when test="${item.rate>=4.4}">
                        background: red;
                </c:when>
                <c:when test="${item.rate>=3}">
                        background: yellow;
                </c:when>
                <c:when test="${item.rate<=0}">
                        background: green;
                </c:when>
                        </c:choose>">${item.rate}%</td>

                <td>${item.diff}</td>
                <td>${item.dea}</td>
                <td>${item.macd}</td>
                    <%--dem-结果分析--%>
                <td>
                    <c:choose>
                        <%--1. 当DIF,DEA两数值位于0轴上方时，说明大势处于多头市场，投资者应当以持股为主要策略。若DIF由下向上与DEA产生交叉，并不代表是一种买入信号，
                        而此时的大盘走势，已是一个短期高点，应当采用高抛低吸的策略--%>
                        <c:when test="${item.diff>=0 && item.dea>=0 && item.diff>item.dea && item.diff>itemNext.dea && item.diff<itemNext.diff}">
                            top live
                        </c:when>
                        <c:when test="${item.diff>itemNext.diff && item.dea>=itemNext.dea && item.macd>item.macd}">
                            come
                        </c:when>
                        <%--在此介绍一种利用MACD与30日均线配合起来寻找底部的办法，可剔除绝大多数的无效信号，留下最真最纯的买入信号。
                        其使用法则：MACD指标中DIF线在0轴以下与DEA线金叉后没有上升至0轴以上，而是很快又与DEA线死叉，此时投资者可等待两线何时再重新金叉，若两线再度金叉（在0轴以下）前后，
                        30日平均线亦拐头上行，这表明底部构筑成功，随后出现一波行情的可能性较大。--%>
                        <%--<c:when test="${(item.diff<0 || itemNext.diff<0) && item.dea<0 && item.diff>item.dea && item.diff>itemNext.diff}">--%>
                            <%--<span style="color: red;">come</span>--%>
                        <%--</c:when>--%>

                        <c:when test="${item.diff>itemNext.diff && item.dea>=itemNext.dea && item.macd>itemNext.macd && item.diff>-8.5 }">
                            <span style="color: red;">up</span>
                        </c:when>

                    </c:choose>
                </td>


                <td style="
                <c:choose>
                <c:when test="${item.k>=90}">
                        /*color: red;*/
                </c:when>
                <c:when test="${item.k<90 &&  item.k>10}">

                </c:when>
                <c:when test="${item.k<=10}">
                        /*color: green;*/
                </c:when>
                        </c:choose>">${item.k}</td>
                    <%--D线是慢速主干线——数值在80以上为超买，数值在20以下为超卖；--%>
                <td style="
                <c:choose>
                <c:when test="${item.d>=80}">
                        /*color: red;*/
                </c:when>
                <c:when test="${item.d<80 &&  item.d>20}">

                </c:when>
                <c:when test="${item.d<=20}">
                        /*color: green;*/
                </c:when>
                        </c:choose>">${item.d}</td>
                    <%--当J值大于90，特别是连续5天以上，股价至少会形成短期头部，反之J值小于10时，特别是连续数天以上，股价至少会形成短期底部。--%>
                <td style="
                <c:choose>
                <c:when test="${item.j>=90}">

                </c:when>
                <c:when test="${item.j<90 &&  item.d>10}">

                </c:when>
                <c:when test="${item.j<=10}">

                </c:when>
                        </c:choose>">${item.j}</td>

                    <%--kdj 结果分析--%>
                <td>
                    <c:choose>

                        <%--如果整体处于80以上的话则代表市场显示为超买区；在这之间的话则显示买卖平衡，变化趋势不是很明显。--%>
                        <c:when test="${item.k>=85 &&  item.d>=85 &&  item.j>=85 && item.k>itemNext.k}">
                            <span style="color: red;">超买区</span>
                        </c:when>

                        <%--2.当K值由较小逐渐大于D值，在图形上显示K线从下方上穿D线，所以在图形上K线向上突破D线时，俗称金叉，即为买进的讯号。--%>
                        <c:when test="${item.k>=itemNext.k && item.d>=itemNext.d && item.j>=itemNext.j}">
                            <span style="color: red;">come</span>
                        </c:when>

                        <%--实战时当K，D线在20以下交叉向上，此时的短期买入的信号较为准确；如果K值在50以下，由下往上接连两次上穿D值，形成右底比左底高的“W底”形态时，后市股价可能会有相当的涨幅。--%>
                        <c:when test="${item.k<=20 &&  item.d<=20 &&  item.k>itemNext.k  && item.d>itemNext.d && item.j>itemNext.j && item.k>item.d  }">
                            <span style="color: red;">up</span>
                        </c:when>

                        <%--实战时当K，D线在80以上交叉向下，此时的短期卖出的信号较为准确；如果K值在50以上，由上往下接连两次下穿D值，形成右头比左头低的“M头”形态时，后市股价可能会有相当的跌幅。--%>
                        <c:when test="${item.k>=79 && item.d>=79 && item.k<itemNext.k }">
                            live-80
                        </c:when>

                        <%--当K值由较大逐渐小于D值，在图形上显示K线从上方下穿D线，显示趋势是向下的，所以在图形上K线向下突破D线时，俗称死叉，即为卖出的讯号。--%>
                        <c:when test="${(item.k+1)<itemNext.k &&  item.d<itemNext.d}">
                            live
                        </c:when>

                        <%--2.如果KDJ三者的值都位于50以上的话，则市场此时显示为多头市场，行情有上涨的趋势；--%>
                      <%--  <c:when test="${item.k>50 &&  item.d>50 &&  item.j>50}">
                            <span style="color: red;">up</span>
                        </c:when>--%>

                        <%--如果三者都位于50以下的话，则显示为空头市场，行情有下降的趋势。--%>
                        <c:when test="${item.k<=40 &&  item.d<=40 &&  item.j<=40 && item.k<itemNext.k}">
                            down
                        </c:when>

                        <%--当KDJ三个值整体处于20以下的话，显示市场为超卖区；--%>
                        <c:when test="${item.k<=20 && item.k<=20 && item.j<=20 && (item.k<itemNext.d || item.d<itemNext.d) }">
                            超卖中
                        </c:when>

                    </c:choose>

                </td>
                <td>${item.date}</td>
                    <%--<fmt:formatDate value="${item.date}"--%>
                    <%--pattern="yyyy-MM-dd HH:mm:ss"--%>
                    <%--timeZone="0"/>--%>
                </tr>
            </c:forEach>
        </c:if>
    </c:forEach>

            </tbody>
        </table><!-- /.table -->
    </div>



<%--


    <p style="color: red">1.当天不能跌</p>
    <p style="color: red">2.前一天资金流入量 大于-100万</p>
    <p style="color: red">3.当天资金交易量 大于2亿</p>
    <p style="color: red">4.dem除了自己前三行必须有一个或者多个up</p>
    <p style="color: red">5.本行有绿色 坚决不买</p>
    <p style="color: red">6.当出现 live-80 坚决卖掉</p>
--%>





</form>


<script type="text/javascript"
        src="${ctx}assets/js/common/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
        src="${ctx}assets/js/common/bootstrap.min.js"></script>



<!-- ECharts单文件引入 -->
<script src="${ctx}assets/js/echart/echarts.js" type="text/javascript"></script>
<%--kdj趋势--%>
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
        var top = ec.init(document.getElementById('kdj'));
        option = {
          title : {
            text: 'kdj-value',
            subtext: '${companyName}-${companyCode}'
          },
          tooltip : {
            trigger: 'axis'
          },
          legend: {
            data:['k','d','j']
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
              data : ${dates}
            }
          ],
          yAxis : [
            {
              type : 'value'
            }
          ],
          series : [
            {
              name:'k',
              type:'line',
              stack: '值',
              data:${ks}
            },
            {
              name:'d',
              type:'line',
              stack: '值',
              data:${ds}
            },
            {
              name:'j',
              type:'line',
              stack: '值',
              data:${js}
            }
          ]
        };

        // 为echarts对象加载数据
        top.setOption(option);
      }
  );
</script>
<%--ddm趋势--%>
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
        var top = ec.init(document.getElementById('ddm'));
        option = {
          title : {
            text: 'ddm-value',
            subtext: '${companyName}-${companyCode}'
          },
          tooltip : {
            trigger: 'axis'
          },
          legend: {
            data:['diff','dea','macd']
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
              data : ${dates}
            }
          ],
          yAxis : [
            {
              type : 'value'
            }
          ],
          series : [
            {
              name:'diff',
              type:'line',
              stack: '值',
              data:${diffs}
            },
            {
              name:'dea',
              type:'line',
              stack: '值',
              data:${deas}
            },
            {
              name:'macd',
              type:'line',
              stack: '值',
              data:${macds}
            }
          ]
        };

        // 为echarts对象加载数据
        top.setOption(option);
      }
  );
</script>

<%--rate趋势--%>
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
            var top = ec.init(document.getElementById('rate'));
            option = {
                title : {
                    text: 'rate-value',
                    subtext: '${companyName}-${companyCode}'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['rate']
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
                        data : ${dates}
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'rate',
                        type:'line',
                        stack: '值',
                        data:${rates}
                    }
                ]
            };

            // 为echarts对象加载数据
            top.setOption(option);
        }
    );
</script>
<%--资金趋势--%>
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
            var money = ec.init(document.getElementById('inflow'));
            option = {
                title : {
                    text: 'inflow-value',
                    subtext: '${companyName}-${companyCode}'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['流入']
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
                        data:${dates}
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'流入',
                        type:'line',
                        smooth:true,
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data:${inflows}
                    }
                ]
            };
            // 为echarts对象加载数据
            money.setOption(option);
        }
    );
</script>
</body>
</html>
