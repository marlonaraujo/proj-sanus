<%@page info="Combo Hospitais" contentType="text/html" pageEncoding="UTF-8" 
        import="pessoa.*"
        import="especialidade.*"
        import="java.util.List"
        import="java.util.*"%>
<select id="hospital" name="hospital">
    <option value="">Selecione uma Especialidade</option>
<%
    int codigo_especialidade = -1;
    try{
        codigo_especialidade = Integer.parseInt(request.getParameter("codigo_hospital"));
    }catch(Exception e){}

    EspecialidadeDAO dao = new EspecialidadeDAO();
    List especialidadeList = dao.retornaEspecialidades();

    for(Iterator i = especialidadeList.iterator(); i.hasNext();) {
        EspecialidadeBean e = (EspecialidadeBean)i.next(); 
        
%><option value="<%=e.getCodigo()%>" <%=(e.getCodigo() == codigo_especialidade)?"selected":""%> ><%=e.getNome()%></option>
<%
   }
%>
</select>