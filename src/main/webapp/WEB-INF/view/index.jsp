<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Good</title>
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
<form action="/dg/index" method="get">
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
                <th><input type="submit" value="查询（ST垃圾，60几开头蓝筹）"></th>
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
                <th>流入</th>
                <th>流入差</th>
                <th>流入率</th>
                <th>主占</th>
                <th>-30入</th>
                <th>-30差</th>
                <th>-30率</th>
                <%--<th>-30主占</th>--%>
                <th>-60入</th>
                <%--<th>-60主占</th>--%>
                <th>昨涨</th>
                <th>前涨</th>
                <%-- <th>日期</th>
                 <th>时间</th>--%>
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
                        <td style="<c:if
                                test="${item.rate>4 && item.rate>item.preDayRate1 && item.preDayRate1>0 && item.rate>item.preDayRate2 && item.preMainMoneyCha1>0}">background: red;</c:if>">
                                ${item.companyName}</td>
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
                        <td>${item.price}</td>
                        <td style="
                        <c:choose>
                        <c:when test="${item.rate>=4}">
                                background: red;
                        </c:when>
                        <c:when test="${item.rate>=3}">
                                background: yellow;
                        </c:when>
                        <c:when test="${item.rate<2}">
                                background: green;
                        </c:when>
                                </c:choose>">${item.rate}%
                        </td>
                        <td>${item.mainMoney}万</td>
                        <td style="
                        <c:choose>
                        <c:when test="${item.preMainMoneyCha>2000}">
                                background: red;
                        </c:when>
                        <c:when test="${item.preMainMoneyCha>1000}">
                                background: yellow;
                        </c:when>
                        <c:when test="${item.preMainMoneyCha>500}">
                                background: green;
                        </c:when>
                        </c:choose>
                                ">${item.preMainMoneyCha}万
                        </td>
                        <td
                                style="
                                <c:choose>
                                <c:when test="${item.preMainMoneyRate>2}">
                                        background: red;
                                </c:when>
                                <c:when test="${item.preMainMoneyRate>1}">
                                        background: yellow;
                                </c:when>
                                <c:when test="${item.preMainMoneyRate>0.8}">
                                        background: green;
                                </c:when>
                                        </c:choose>"
                                <c:if test="${item.preMainMoneyRate<0}">style="background: red;" </c:if>>${item.preMainMoneyRate}%
                        </td>

                        <td>${item.mainRate}%</td>

                            <%---0.5h--%>
                        <td>${item.preTimeMainMoney1}万</td>
                        <td <td style="
                    <c:choose>
                        <c:when test="${item.preMainMoneyCha1>2000}">
                            background: red;
                        </c:when>
                        <c:when test="${item.preMainMoneyCha1>1000}">
                            background: yellow;
                        </c:when>
                        <c:when test="${item.preMainMoneyCha1>500}">
                            background: green;
                        </c:when>
                    </c:choose>
                    "> ${item.preMainMoneyCha1}万
                        </td>
                        <td
                                style="
                                <c:choose>
                                <c:when test="${item.preMainMoneyRate1>2}">
                                        background: red;
                                </c:when>
                                <c:when test="${item.preMainMoneyRate1>1}">
                                        background: yellow;
                                </c:when>
                                <c:when test="${item.preMainMoneyRate1>0.8}">
                                        background: green;
                                </c:when>
                                        </c:choose>"
                                <c:if test="${item.preMainMoneyRate1<0}">style="background: red;" </c:if>>${item.preMainMoneyRate1}%
                        </td>

                            <%--<td>${item.preTimeRate1}%</td>--%>

                        <td>${item.preTimeMainMoney2}万</td>
                            <%--<td>${item.preTimeRate2}%</td>--%>
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
                        </td>
                            <%--   <td>${item.date}</td>
                               <td>${item.time}</td>--%>
                        <td><fmt:formatDate value="${item.originalTime}"
                                            pattern="yyyy-MM-dd HH:mm:ss"
                                            timeZone="0"/></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table><!-- /.table -->
    </div>
</form>
</body>
</html>
