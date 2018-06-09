$(document).ready(function () {
  $(".companyName").mouseover(function () {
    var id=$(this).attr("id");
    if($('#info' + id).attr("data-content")=="") {
      var htmlValue = "";
      $.ajax({
        url: "/day/info?companyCode=" + id,
        dataType: "json",
        success: function (result) {
          if (result.topValue != undefined && result.topValue != null
              && result.topValue.length > 0) {
            htmlValue += "top:" + result.topValue[0].endPrice +";";
            var price=$("#price-"+id).text();
            if(price>result.topValue[0].endPrice){
              var priceCha=price-result.topValue[0].endPrice;
              htmlValue+="cha:"+((priceCha/price)*100).toFixed(2)+"%;";
            }
          }
          if (result.avg != undefined && result.avg != null) {
            htmlValue += "avg:" + result.avg;
          }

          $('#info' + id).attr("data-content", htmlValue);
        }
      });
    }
    $('#info' + $(this).attr("id")).popover('show');
  });
  $(".companyName").mouseout(function () {
    $('#info' + $(this).attr("id")).popover('hide');
  });
});