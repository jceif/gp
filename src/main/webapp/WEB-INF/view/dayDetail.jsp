<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>data-day-detail</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}assets/css/common/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}assets/css/common/list.css">

</head>
<body>


<form action="/day/index" method="get">




    <div style="margin:0 auto; width: 90%;text-align: center;">
        <table style="width: 100%;">
            <tbody>
            <tr>
                <th>index</th>
                <th>name</th>
                <th>code</th>
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
                <th>k</th>
                <th>d</th>
                <th>j</th>
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
                <td><a href="/data/chart?companyCode=${company.code}&date=${item.date}">${company.name}</a></td>
                <td>
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


                <td>${item.date}</td>

                </tr>
            </c:forEach>
        </c:if>
    </c:forEach>

            </tbody>
        </table><!-- /.table -->
    </div>

    <div id="rate" style="height:400px"></div>
    <div id="kdj" style="height:400px"></div>
    <div id="ddm" style="height:400px"></div>
    <div id="inflow" style="height:400px"></div>

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

              data:${ks}
            },
            {
              name:'d',
              type:'line',

              data:${ds}
            },
            {
              name:'j',
              type:'line',

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

              data:${diffs}
            },
            {
              name:'dea',
              type:'line',

              data:${deas}
            },
            {
              name:'macd',
              type:'line',

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
