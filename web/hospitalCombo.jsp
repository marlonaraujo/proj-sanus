<%@page info="Combo Hospitais" contentType="text/html" pageEncoding="UTF-8" 
        import="pessoa.*"
        import="hospital.*"
        import="java.util.List"
        import="java.util.*"%>
<select id="codigo-hospital" name="codigo-hospital">
    <option value="">Selecione um Hospital</option>
<%
    int codigo_hospital = -1;
    try{
        codigo_hospital = Integer.parseInt(request.getParameter("codigo_hospital"));
    }catch(Exception e){}

    HospitalDAO dao = new HospitalDAO();
    List hospitalList = dao.retornarTodos();

    for(Iterator i = hospitalList.iterator(); i.hasNext();) {
        HospitalBean h = (HospitalBean)i.next(); 
        
%><option value="<%=h.getCodigo()%>" <%=(h.getCodigo() == codigo_hospital)?"selected":""%> ><%=h.getNome()%></option>
<%
   }
%>
</select>