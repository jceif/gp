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
    <link rel="stylesheet" type="text/css" href="${ctx}assets/css/common/list.css">

</head>
<body>
<form action="/data/" method="get">
    <div style="margin:0 auto; width: 90%;text-align: center;">
        <%-- <div style="margin:0 auto; width: 100%;text-align: center;height: 100px;margin: 10px;">--%>
        <table style="width: 100%;">
            <tr>
                <th>净流入：<input type="number" name="mainMoney"
                               value="${mainMoney}">
                </td>
                <th>当前日期：
                    <select name="date">
                        <option value="">请选择</option>
                        <c:forEach items="${formatDates}" var="item"
                                   varStatus="var">
                            <option value="${item}"
                                    <c:if test="${item==date}">selected</c:if>>${item}</option>
                        </c:forEach>
                    </select>
                </th>
                <th>前一个交易日期：
                    <select name="preDate1">
                        <option value="">请选择</option>
                        <c:forEach items="${formatDates1}" var="item"
                                   varStatus="var">
                            <option value="${item}"
                                    <c:if test="${item==preDate1}">selected</c:if>>${item}</option>
                        </c:forEach>
                    </select>
                </th>
                <th>前二个交易日期：
                    <select name="preDate2">
                        <option value="">请选择</option>
                        <c:forEach items="${formatDates2}" var="item"
                                   varStatus="var">
                            <option value="${item}"
                                    <c:if test="${item==preDate2}">selected</c:if>>${item}</option>
                        </c:forEach>
                    </select>
                </th>

            </tr>
            <tr>
                <th>时间：
                    <select name="time">
                        <option value="930"
                                <c:if test="${time=='930'}">selected</c:if>>9:30
                        </option>
                        <option value="1000"
                                <c:if test="${time=='1000'}">selected</c:if>>
                            10:00
                        </option>
                        <option value="1030"
                                <c:if test="${time=='1030'}">selected</c:if>>
                            10:30
                        </option>
                        <option value="1100"
                                <c:if test="${time=='1100'}">selected</c:if>>
                            11:00
                        </option>
                        <option value="1130"
                                <c:if test="${time=='1130'}">selected</c:if>>
                            11:30
                        </option>
                        <option value="1300"
                                <c:if test="${time=='1300'}">selected</c:if>>
                            13:00
                        </option>
                        <option value="1330"
                                <c:if test="${time=='1330'}">selected</c:if>>
                            13:30
                        </option>
                        <option value="1400"
                                <c:if test="${time=='1400'}">selected</c:if>>
                            14:00
                        </option>
                        <option value="1430"
                                <c:if test="${time=='1430'}">selected</c:if>>
                            14:30
                        </option>
                        <option value="1500"
                                <c:if test="${time=='1500'}">selected</c:if>>
                            15:00
                        </option>
                    </select>
                </th>
                <th>价格：<input type="number" name="price" value="${price}"></th>
                <th>涨跌：<input type="text" name="rate" value="${rate}"></th>
                <th><input type="submit" value="查询">&nbsp;
                    <a href="/data/topTimeList" target="_blank">tj1</a>&nbsp;
                    <a href="/day/topRateList" target="_blank">tj2</a> &nbsp;
                    <a href="/day/topInflowList" target="_blank">tj3</a> &nbsp;
                </th>
            </tr>
        </table>
        <%--</div>--%>

        <table style="width: 100%;">
            <tbody>
            <tr>
                <th>号</th>
                <th>类型</th>
                <th>名称</th>
                <th>编码</th>
                <th>价格</th>
                <th>涨幅</th>
                <th>30涨</th>
                <th>60涨</th>
                <th>流入</th>
                <th>入差</th>
                <th>入率</th>
                <th>主占</th>
                <th>30入</th>
                <th>30入差</th>
                <th>30入率</th>
                <th>60入</th>
                <th>上值</th>
                <th>上上值</th>
                <th>原日期</th>
            </tr>
            <c:forEach items="${dayGoods}" var="item" varStatus="var">
                <c:if test="${ fn:substring(item.companyCode ,0,3)!='300'}">
                    <tr>
                            <%--当前--%>
                        <td>${var.count}</td>
                        <td>
                            <c:choose>
                                <c:when test="${ fn:substring(item.companyCode ,0,3)=='600' or fn:substring(item.companyCode ,0,2)=='60'}">
                                    沪A
                                </c:when>
                                <c:when test="${ fn:substring(item.companyCode ,0,3)=='000'}">
                                    深主
                                </c:when>
                                <c:when test="${ fn:substring(item.companyCode ,0,3)=='002'}">
                                    深中
                                </c:when>
                                <c:when test="${ fn:substring(item.companyCode ,0,3)=='900'}">
                                    沪B
                                </c:when>
                                <c:when test="${ fn:substring(item.companyCode ,0,3)=='200'}">
                                    深B
                                </c:when>
                                <c:when test="${ fn:substring(item.companyCode ,0,3)=='300'}">
                                    创业板
                                </c:when>
                                <c:otherwise>
                                    无
                                </c:otherwise>
                            </c:choose>
                        </td>
                            <%--公司名称--%>
                        <td style="
                        <c:if test="${item.rate>4.1 && item.rate>item.preDayRate1 }">
                        <c:if test="${item.preDayRate1-item.preDayRate2>-2}">
                        <c:if test="${(item.preDayRate1>0 && item.preDayRate1<3.37) || item.preDayRate1>8.9}">
                        <c:if test="${(item.preMainMoneyCha>300  && item.preMainMoneyCha1>=0) || item.mainMoney>5299}">
                        <c:if test="${item.preTimeMainMoney2>0}">
                        <c:if test="${item.mainMoney>6900}">
                        <c:if test="${item.preDayRate2>-3.79}">
                                background: green;
                        </c:if>
                        </c:if>
                        </c:if>
                        </c:if>
                        </c:if>
                        </c:if>
                                </c:if>" class="companyName"
                            id="${item.companyCode}">
                            <c:choose>
                                <c:when test="${item.rate>item.preTimeRate1 && item.preTimeRate1>item.preTimeRate2}">
                                    <span class="glyphicon glyphicon-arrow-up"
                                          style="padding: 0px;margin: 0px;"
                                          aria-hidden="true"></span>
                                </c:when>
                                <c:when test="${item.rate<item.preTimeRate1}">
                                    <span class="glyphicon glyphicon-arrow-down"
                                          style="padding: 0px;margin: 0px;"
                                          aria-hidden="true"></span>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                            <a href="/day/detail?companyCode=${item.companyCode}&count=30" target="_blank"
                               style="color: black;">${item.companyName}</a>

                            <button type="button" id="info${item.companyCode}"
                                    style="display: none"
                                    class="btn btn-default"
                                    data-container="body" data-toggle="popover"
                                    data-placement="right"
                                    data-content=""></button>
                        </td>
                            <%--公司编码--%>
                        <td>
                            <c:choose>
                                <c:when test="${ fn:substring(item.companyCode ,0,3)=='600' or fn:substring(item.companyCode ,0,2)=='60'}">
                                    <a href="http://quote.eastmoney.com/concept/sh${item.companyCode}.html?from=classic&eventcode=Web_quote_entrance2"
                                       target="_blank">${item.companyCode}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="http://quote.eastmoney.com/concept/sz${item.companyCode}.html?from=classic&eventcode=Web_quote_entrance2"
                                       target="_blank">${item.companyCode}</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                            <%--当前价格--%>
                        <td><span
                                id="price-${item.companyCode}">${item.price}</span>
                        </td>
                            <%--涨跌幅度--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.rate>=4.4}">
                                background: red;
                        </c:when>
                        <c:when test="${item.rate>=3}">
                                background: yellow;
                        </c:when>
                        <c:when test="${item.rate<0}">
                                background: green;
                        </c:when>
                                </c:choose>">${item.rate}%
                        </td>
                            <%--半小时之前的涨幅--%>
                        <td>${item.preTimeRate1}%</td>
                            <%--一小时之前的涨幅--%>
                        <td>${item.preTimeRate2}%</td>

                            <%--当前净流入--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.mainMoney>7000}">
                                background: red;
                        </c:when>
                        <c:when test="${item.mainMoney>5000}">
                                background: yellow;
                        </c:when>
                        </c:choose>
                                ">${item.mainMoney}万
                        </td>
                            <%--流入差--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.preMainMoneyCha>3000}">
                                background: red;
                        </c:when>
                        <c:when test="${item.preMainMoneyCha>2000}">
                                background: yellow;
                        </c:when>
                        <c:when test="${item.preMainMoneyCha<0}">
                                background: green;
                        </c:when>
                        </c:choose>
                                ">${item.preMainMoneyCha}万
                        </td>
                            <%--流入率--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.preMainMoneyRate>2}">
                                background: red;
                        </c:when>
                        <c:when test="${item.preMainMoneyRate>1.05}">
                                background: yellow;
                        </c:when>
                        <c:when test="${item.preMainMoneyRate<0}">
                                background: green;
                        </c:when>
                                </c:choose>"
                        >${item.preMainMoneyRate}%
                        </td>
                            <%--主力流入占比--%>
                        <td>${item.mainRate}%</td>

                            <%---0.5h--%>
                            <%--上半个小时的主流入--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.preTimeMainMoney1<0}">
                                background: green;
                        </c:when>
                                </c:choose>">${item.preTimeMainMoney1}万
                        </td>
                            <%--上半个小时的主流如差--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.preMainMoneyCha1>2500}">
                                background: red;
                        </c:when>
                        <c:when test="${item.preMainMoneyCha1>1000}">
                                background: yellow;
                        </c:when>
                        <c:when test="${item.preMainMoneyCha1<0}">
                                background: green;
                        </c:when>
                                </c:choose>"> ${item.preMainMoneyCha1}万
                        </td>
                            <%--上半个小时主流入占比--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.preMainMoneyRate1>2}">
                                background: red;
                        </c:when>
                        <c:when test="${item.preMainMoneyRate1>1}">
                                background: yellow;
                        </c:when>
                        <c:when test="${item.preMainMoneyRate1<0}">
                                background: green;
                        </c:when>
                                </c:choose>">
                                ${item.preMainMoneyRate1}%
                        </td>


                            <%--上一个个小时的主流入--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.preTimeMainMoney2<0}">
                                background: green;
                        </c:when>
                                </c:choose>"> ${item.preTimeMainMoney2}万
                        </td>

                            <%--昨天涨幅--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.preDayRate1>3}">
                                background: red;
                        </c:when>
                        <c:when test="${item.preDayRate1>2}">
                                background: yellow;
                        </c:when>
                        <c:when test="${item.preDayRate1<0}">
                                background: green;
                        </c:when>
                                </c:choose>">${item.preDayRate1}%
                        </td>
                            <%--前天涨幅--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.preDayRate2>3}">
                                background: red;
                        </c:when>
                        <c:when test="${item.preDayRate2>2}">
                                background: yellow;
                        </c:when>
                        <c:when test="${item.preDayRate2<0}">
                                background: green;
                        </c:when>
                                </c:choose>">${item.preDayRate2}%
                            <c:if test="${item.preDayRate1<item.preDayRate2}">
                                -
                            </c:if>
                        </td>
                            <%--   <td>${item.date}</td>
                               <td>${item.time}</td>--%>
                            <%--数据时间--%>
                        <td>
                            <fmt:formatDate value="${item.originalTime}"
                                            pattern="yyyy-MM-dd HH:mm:ss"
                                            timeZone="0"/></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table><!-- /.table -->
            <p style="color: red;text-align: left;">1.rate<0&小于10亿:不买</p>
            <p></p>
            <p style="color: red;text-align: left;">2.next rate<-1.5: execute</p>
            <p></p>
            <p style="color: red;text-align: left;">3.next rate>3: wait three</p>
            <p></p>
            <p style="color: red;text-align: left;">4.three rate<-1.5: execute</p>
            <p></p>
    </div>


</form>


<script type="text/javascript"
        src="${ctx}assets/js/common/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
        src="${ctx}assets/js/common/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}assets/js/dayGood/index.js"></script>
</body>
</html>
