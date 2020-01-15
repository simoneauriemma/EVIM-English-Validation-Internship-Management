$(document)
		.ready(
				function() {
					$(document).on(
							"click",
							"#generateExcelAccepted",
							function() {
								$(".preloader").show();
								window.location.href = absolutePath
										+ "/ServletAdmin?flag=5";
								$(".preloader").hide();
								showData();
							});

					$(document).on(
							"click",
							"#generateExcelRefused",
							function() {
								$(".preloader").show();
								window.location.href = absolutePath
										+ "/ServletAdmin?flag=6";
								$(".preloader").hide();
								showData();
							});

					$(document)
							.on(
									"click",
									".toRefused",
									function() {
										var idRequest = $(this).data(
												"idrequest");

										if (idRequest != undefined
												&& idRequest > 0) {
											if (confirm("Conferma il cambio di stato della richiesta?")) {
												$(".preloader").show();

												$
														.ajax({
															url : absolutePath
																	+ "/ServletAdmin",
															type : "POST",
															dataType : 'JSON',
															async : false,
															data : {
																"idRequest" : idRequest,
																"flag" : 4
															},
															success : function(
																	msg) {
																if (!msg.result) {
																	showAlert(
																			1,
																			msg.error);
																} else {
																	showAlert(
																			0,
																			msg.content);
																	setTimeout(
																			function() {
																				showData();
																			},
																			2000);
																}
															},
															error : function(
																	msg) {
																showAlert(1,
																		"Impossibile Recuperare i dati.");
															}
														});

												$(".preloader").hide();
											}
										} else {
											showAlert(1, "Errore parametri.");
										}
									});

					$(document)
							.on(
									"click",
									".toAccepted",
									function() {
										var idRequest = $(this).data(
												"idrequest");

										if (idRequest != undefined
												&& idRequest > 0) {
											if (confirm("Conferma il cambio di stato della richiesta?")) {
												$(".preloader").show();

												$
														.ajax({
															url : absolutePath
																	+ "/ServletAdmin",
															type : "POST",
															dataType : 'JSON',
															async : false,
															data : {
																"idRequest" : idRequest,
																"flag" : 3
															},
															success : function(
																	msg) {
																if (!msg.result) {
																	showAlert(
																			1,
																			msg.error);
																} else {
																	showAlert(
																			0,
																			msg.content);
																	setTimeout(
																			function() {
																				showData();
																			},
																			2000);
																}
															},
															error : function(
																	msg) {
																showAlert(1,
																		"Impossibile Recuperare i dati.");
															}
														});

												$(".preloader").hide();
											}
										} else {
											showAlert(1, "Errore parametri.");
										}
									});

					$(document)
							.on(
									"submit",
									"#saveSurname",
									function() {
										var idUser = $("#saveSurname .confirm")
												.data("iduser");
										var newSurname = $(
												"#saveSurname #surname").val();
										if (idUser != undefined
												&& idUser.length > 0
												&& newSurname != undefined) {
											$(".preloader").show();

											$
													.ajax({
														url : absolutePath
																+ "/ServletCommon",
														type : "POST",
														dataType : 'JSON',
														async : false,
														data : {
															"idUser" : idUser,
															"newSurname" : newSurname,
															"flag" : 3
														},
														success : function(msg) {
															if (!msg.result) {
																showAlert(
																		1,
																		msg.error);
															} else {
																showAlert(
																		0,
																		msg.content);
																$(
																		"#defaultModal")
																		.modal(
																				"hide");
																setTimeout(
																		function() {
																			showData();
																		}, 2000);
															}
														},
														error : function(msg) {
															showAlert(1,
																	"Impossibile Recuperare i dati.");
														}
													});

											$(".preloader").hide();
										} else {
											showAlert(1, "Errore parametri.");
										}
									});

					/* New surname updater start */
					$(document)
					.on(
						"click",
						"#changeSurname",
						function() {
							let idUser = $(this).data("iduser");
							let oldSurname = $(this).data("surname");
							let newSurname = prompt("Immetti il nuovo cognnome: ",	oldSurname);
								if (newSurname) {
									$.ajax({
										url : absolutePath
										+ "/ServletCommon",
										type : "POST",
										dataType : 'JSON',
										async : false,
										data : {
											"idUser" : idUser,
											"newSurname" : newSurname,
											"flag" : 3
										},
										success : function(msg) {
											if (!msg.result) {
												showAlert(
														1,
														msg.error);
											} else {
												showAlert(
														0,
														msg.content);
												$(
														"#defaultModal")
														.modal(
																"hide");
												setTimeout(
														function() {
															showData();
														}, 2000);
											}
										},
										error : function(msg) {
											showAlert(1,
													"Impossibile Recuperare i dati.");
										}
									});
								} else {
									console.error("U0JBR0xJLCBGRVJSVUNDSSBUUk9JQQ==");
								}
							});
					/* New surname updater end */

					$(document)
							.on(
									"submit",
									"#saveName",
									function() {
										var idUser = $("#saveName .confirm")
												.data("iduser");
										var newName = $("#saveName #name")
												.val();
										if (idUser != undefined
												&& idUser.length > 0
												&& newName != undefined) {
											$(".preloader").show();

											$
													.ajax({
														url : absolutePath
																+ "/ServletCommon",
														type : "POST",
														dataType : 'JSON',
														async : false,
														data : {
															"idUser" : idUser,
															"newName" : newName,
															"flag" : 2
														},
														success : function(msg) {
															if (!msg.result) {
																showAlert(
																		1,
																		msg.error);
															} else {
																showAlert(
																		0,
																		msg.content);
																$(
																		"#defaultModal")
																		.modal(
																				"hide");
																setTimeout(
																		function() {
																			showData();
																		}, 2000);
															}
														},
														error : function(msg) {
															showAlert(1,
																	"Impossibile Recuperare i dati.");
														}
													});

											$(".preloader").hide();
										} else {
											showAlert(1, "Errore parametri.");
										}
									});

					
					/* New name updater start */
					$(document)
					.on(
						"click",
						"#changeName",
						function() {
							let idUser = $(this).data("iduser");
							let oldName = $(this).data("name");
							let newName = prompt("Immetti il nuovo nome: ",	oldName);
								if (newName) {
									$.ajax({
										url : absolutePath
										+ "/ServletCommon",
										type : "POST",
										dataType : 'JSON',
										async : false,
										data : {
											"idUser" : idUser,
											"newName" : newName,
											"flag" : 2
										},
										success : function(msg) {
											if (!msg.result) {
												showAlert(
														1,
														msg.error);
											} else {
												showAlert(
														0,
														msg.content);
												$(
														"#defaultModal")
														.modal(
																"hide");
												setTimeout(
														function() {
															showData();
														}, 2000);
											}
										},
										error : function(msg) {
											showAlert(1,
													"Impossibile Recuperare i dati.");
										}
									});
								} else {
									console.error("U0JBR0xJLCBGRVJSVUNDSSBUUk9JQQ==");
								}
							});
					/* New name updater end */

					$(document)
							.on(
									"click",
									".toWorkingEducationAdvice",
									function() {
										var type = $(this).data("type");
										var idRequest = $(this).data(
												"idrequest");

										if (type != undefined
												&& (type == 1 || type == 2)
												&& idRequest != undefined
												&& idRequest > 0) {
											if (confirm("Conferma il cambio di stato della richiesta?")) {
												$(".preloader").show();

												$
														.ajax({
															url : absolutePath
																	+ "/ServletAdmin",
															type : "POST",
															dataType : 'JSON',
															async : false,
															data : {
																"type" : type,
																"idRequest" : idRequest,
																"flag" : 2
															},
															success : function(
																	msg) {
																if (!msg.result) {
																	showAlert(
																			1,
																			msg.error);
																} else {
																	showAlert(
																			0,
																			msg.content);
																	setTimeout(
																			function() {
																				showData();
																			},
																			2000);
																}
															},
															error : function(
																	msg) {
																showAlert(1,
																		"Impossibile Recuperare i dati.");
															}
														});

												$(".preloader").hide();
											}
										} else {
											showAlert(1, "Errore parametri.");
										}
									});

					$(document)
							.on(
									"click",
									".verifyCertificate",
									function() {
										var mail = $(this).data("mail");
										var certSerial = $(this).data(
												"certserial");
										var subject = "Certification Validation";

										if (mail != undefined
												&& certSerial != undefined
												&& mail.length > 0
												&& certSerial.length > 0) {
											var body = "Dear, I would know if this certificate is valid: Certificate Serial "
													+ certSerial
													+ ".\r\n\rThank you";
											var uri = "mailto:" + mail
													+ "?subject=";
											uri += encodeURIComponent(subject);
											uri += "&body=";
											uri += encodeURIComponent(body);
											window.open(uri);
											return false;
										}
									});

				});

function showData() {

	$(".preloader").show();

	$.ajax({
		url : absolutePath + "/ServletAdmin",
		type : "POST",
		dataType : 'JSON',
		async : false,
		data : {
			"flag" : 1
		},
		success : function(msg) {
			if (!msg.result) {
				showAlert(1, msg.error);
			} else {
				$("#bodyAdminTable").html(msg.content);
			}
		},
		error : function(msg) {
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});

	$(".preloader").hide();
}