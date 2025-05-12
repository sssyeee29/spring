<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<%@ include file="../includes/header.jsp" %> <!-- header 임포트 시키기 -->

         <div class="row">
             <div class="col-lg-12">
                 <h1 class="page-header">Tables</h1>
             </div>
             <!-- /.col-lg-12 -->
         </div>
         <!-- /.row -->
         <div class="row">
             <div class="col-lg-12">
                 <div class="panel panel-default">
                     <div class="panel-heading">
                        	Board Register
                     </div>
                     <!-- /.panel-heading -->
                     <div class="panel-body">
                        <form action="/board/register" method="post">
                        	<div class="form-group">
                        		<label>Title</label><input class="form-control" name="title">
                        	</div>
                        	
                        	<div class="form-group">
                        		<label>Text area</label>
                        		<textarea rows="3" class="form-control" name="content"></textarea>
                       		</div>
                       		
                       		<div class="form-group">
                       			<label>Writer</label><input class="form-control" name="writer">
                       		</div>
                       		
                       		<button class="btn btn-info" type="submit">Submit Button</button>
                       		<button class="btn btn-default" type="reset">Reset Button</button>
                        </form>
                     </div>
                     <!-- /.panel-body -->
                 </div>
                 <!-- /.panel -->
             </div>
             <!-- /.col-lg-6 -->
         </div>
         <!-- /.row -->
   
     
<%@ include file="../includes/footer.jsp" %>