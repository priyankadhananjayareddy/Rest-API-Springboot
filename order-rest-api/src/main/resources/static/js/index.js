$(document).ready(function() {
	$("#orderTable").hide();
	$("#getAllOrdersButton").click(function() {
		$.ajax({
			type: "GET",
			url: "/order/getAllOrders",
			data: null,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(response) {
				var tableBodyHTML = '';
				tableBodyHTML = tableBodyHTML + '<tbody>';
				$.each(response, function(index) {
					tableBodyHTML = tableBodyHTML + '<tr>';
						tableBodyHTML = tableBodyHTML + '<td>'+response[index].orderId+'</td>';
						tableBodyHTML = tableBodyHTML + '<td>'+response[index].transactionId+'</td>';
						tableBodyHTML = tableBodyHTML + '<td>'+response[index].customerName+'</td>';
						tableBodyHTML = tableBodyHTML + '<td>'+response[index].productName+'</td>';
						tableBodyHTML = tableBodyHTML + '<td>'+response[index].price+'</td>';
						tableBodyHTML = tableBodyHTML + '<td>'+response[index].fullAddress+'</td>';
						tableBodyHTML = tableBodyHTML + '<td>'+response[index].paymentType+'</td>';
						tableBodyHTML = tableBodyHTML + '<td>'+response[index].orderStatus+'</td>';
					tableBodyHTML = tableBodyHTML + '</tr>';
            		
        		});
        		tableBodyHTML = tableBodyHTML + '</tbody>';
        		$("#orderTable").append(tableBodyHTML);
        		$("#orderTable").show();
        		$("#orderTable").DataTable();
			},
			failure: function(response) {
				alert(response);
			},
			error: function(response) {
				alert(response);
			}
		});
	});
});