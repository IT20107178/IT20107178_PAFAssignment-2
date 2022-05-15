$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validatePowerForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidPowerIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
		url : "PowerAPI",  
		type : type,  
		data : $("#formPower").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onPowerSaveComplete(response.responseText, status);  
		} 
	}); 
});


function onPowerSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divPowerGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidPowerIDSave").val("");  
	$("#formPower")[0].reset(); 
}


//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidPowerIDSave").val($(this).data("powerid"));     
	$("#cusId").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#telNo").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#date").val($(this).closest("tr").find('td:eq(2)').text());  
	$("#amount").val($(this).closest("tr").find('td:eq(3)').text());
	$("#cardNo").val($(this).closest("tr").find('td:eq(4)').text());     
	$("#postalNo").val($(this).closest("tr").find('td:eq(5)').text());  
	
});


//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "PowerAPI",
		type : "DELETE",
		data : "poId=" + $(this).data("powerid"),
		dataType : "text",
		complete : function(response, status)
		{
			onPowerDeleteComplete(response.responseText, status);   
		}
	}); 
});


function onPowerDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divPowerGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}

//CLIENT-MODEL================================================================
function validatePowerForm()
{
	// 	CUS NAME
	if ($("#cusname").val().trim() == "")
	{
		return "Insert Item Name.";
	}
	
	// POWER ACCOUNT NO
	if ($("#pAccNo").val().trim() == "")
	{
		return "Insert Item No.";
	}
	
	// PSUNIT 
	if ($("#psUnit ").val().trim() == "")
	{
		return "Insert Unit.";
	}
	
	// POWER DATE-------------------------------
	if ($("#pDate").val().trim() == "")
	{
		return "Insert Date.";
	}
	
	// is numerical value
	var tmpAmount = $("#amount").val().trim();
	if (!$.isNumeric(tmpAmount))
	{
		return "Insert a numerical value for Total Amount.";
	}
	
	// convert to decimal price
	$("#itemAmount").val(parseFloat(tmpAmount).toFixed(2));
	
	
	{
		return "Insert Item Postal No.";
	}
	return true;
}

