<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <%if (rowsPerPage<rowNumber) {%>
  
    <%if(pageIndex>=rowsPerPage){%>
    	<li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=1">至第一頁 </a></li> &nbsp;
        <li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>">上一頁 </a></li> &nbsp;
    <%}%>
    
    <%if(pageIndex<rowsPerPage){%>
        <li class="page-item"><a class="page-link" href="#" disabled="disabled" style="color:darkgray;">上一頁 </a></li> &nbsp;
    <%}%>    
    
     <%if (pageNumber>0){%>
     <li class="page-item"><a class="page-link" href="#"> <%=whichPage%>/<%=pageNumber%> </a></li>&nbsp;
	<%}%>
	
    <%if(pageIndex<pageIndexArray[pageNumber-1]){%>
        <li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>">下一頁 </a></li>&nbsp;
        <li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>">最後一頁</a></li>&nbsp;
    <%}%>
    
    <%if(pageIndex>=pageIndexArray[pageNumber-1]){%>
    <li class="page-item"><a class="page-link" href="#" disabled="disabled" style="color:darkgray;">下一頁 </a></li>&nbsp;
    <%}%>
    
  <%}%>  