<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Side Navbar -->
<nav class="side-navbar">
  <!-- Sidebar Header-->
  <div class="sidebar-header d-flex align-items-center">
    <%--<div class="avatar"><img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>--%>
    <%--<div class="title">--%>
      <%--<h1 class="h4">Mark Stephen</h1>--%>
      <%--<p>Web Designer</p>--%>
    <%--</div>--%>
  </div>
  <!-- Sidebar Navidation Menus-->
  <span class="heading">Main</span>
  <ul class="list-unstyled">
    <li <c:if test="${location == 'species' || location == 'manage'}">class="active"</c:if>><a href="/species">종목록생성기</a></li>
    <%--<li><a href="index.html"> <i class="icon-home"></i>Home </a></li>--%>
    <%--<li <c:if test="${location == 'chart'}">class="active"</c:if>><a href="/chart">Charts</a></li>--%>
    <%--<li <c:if test="${location == 'form'}">class="active"</c:if>><a href="/form">Forms</a></li>--%>
    <%--<li><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>Example dropdown </a>--%>
      <%--<ul id="exampledropdownDropdown" class="collapse list-unstyled ">--%>
        <%--<li><a href="#">Page</a></li>--%>
        <%--<li><a href="#">Page</a></li>--%>
        <%--<li><a href="#">Page</a></li>--%>
      <%--</ul>--%>
    <%--</li>--%>
    <%--<li><a href="login.html"> <i class="icon-interface-windows"></i>Login page </a></li>--%>
  </ul>
</nav>