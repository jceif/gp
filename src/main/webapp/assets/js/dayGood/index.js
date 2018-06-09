$(document).ready(function () {
  $(".companyName").each(function () {
    var id=$(this).attr("id");
    if($('#info' + id).attr("data-content")=="") {
      var htmlValue = "";
      $.ajax({
        url: "/day/info?companyCode=" + id,
        dataType: "json",
        success: function (result) {
          if (result.avg != undefined && result.avg != null) {
            htmlValue += "avg:" + result.avg + ";</br>";
          }
          if (result.topValue != undefined && result.topValue != null
              && result.topValue.length > 0) {
            htmlValue += "top:" + result.topValue[0].endPrice + ",date:"
                + result.topValue[0].date + ";";
          }
          $('#info' + id).attr("data-content", htmlValue);
        }
      });
    }
  });

  $(".companyName").mouseover(function () {
    $('#info' + $(this).attr("id")).popover('show');
  });
  $(".companyName").mouseout(function () {
    $('#info' + $(this).attr("id")).popover('hide');
  });
});