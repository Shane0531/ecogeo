<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>종목록생성기</title>
  <script type="text/javascript">

    $(function () {
      $( ".item-group-container" ).append( "<div class='item-group has-shadow'>"+
        "<div class='input-group'>"+
        "<input type='text' class='form-control' placeholder='그룹이름'>"+
        "<div class='input-group-append'>"+
        "<button type='button' class='btn btn-danger' onclick='deleteItem(this)'>삭제</button></div></div>"+
        "<div class='item-group-textarea'><textarea class='form-control' rows='18' id='comment'></textarea></div></div>"
      );

    });
    function addItemGroup() {
      $( ".item-group-container" ).append( "<div class='item-group has-shadow'>"+
      "<div class='input-group'>"+
        "<input type='text' class='form-control' placeholder='그룹이름'>"+
        "<div class='input-group-append'>"+
        "<button type='button' class='btn btn-danger' onclick='deleteItem(this)'>삭제</button></div></div>"+
        "<div class='item-group-textarea'><textarea class='form-control' rows='18' id='comment'></textarea></div></div>"
        );
    }

    function deleteItem(obj) {
      var item = obj.parentNode.parentNode.parentNode;
      item.remove();
    }
  </script>
</head>
<body>
<div>

  <header class="page-header">
    <div class="container-fluid">
      <h2 class="no-margin-bottom">종목록생성기</h2>
    </div>
  </header>
  <!-- Dashboard Counts Section-->
  <section class="dashboard-counts no-padding-bottom">
    <div class="container-fluid">
      <div class="row bg-white has-shadow">

        <button class="btn btn-primary" onclick="addItemGroup()">+ 그룹추가</button>

        <div class="item-group-container">

          <%--<div class="item-group has-shadow">--%>
            <%--<div class="input-group">--%>
              <%--<input type="text" class="form-control" placeholder="그룹이름">--%>
              <%--<div class="input-group-append">--%>
                <%--<button type="button" class="btn btn-danger">삭제</button>--%>
              <%--</div>--%>
            <%--</div>--%>

            <%--<div class="item-group-textarea">--%>
              <%--<textarea class="form-control" rows="18" id="comment"></textarea>--%>
            <%--</div>--%>
          <%--</div>--%>

        </div>

        <div class="item-fetch-container">
          <button class="btn btn-warning">가져오기</button>
        </div>
      </div>

    </div>

  </section>



</div>
</body>
</html>