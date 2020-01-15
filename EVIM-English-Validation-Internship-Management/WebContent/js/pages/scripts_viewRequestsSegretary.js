$(document)
.ready(
		function() {

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
					".toWorkingAdmin",
					function() {
						var idRequest = $(this).data(
						"idrequest");
						if (idRequest != undefined
								&& idRequest > 0) {
							if (confirm("Conferma l'inoltro della richiesta all'amministratore?")) {
								$(".preloader").show();

								$
								.ajax({
									url : absolutePath
									+ "/ServletSecretary",
									type : "POST",
									dataType : 'JSON',
									async : false,
									data : {
										"flag" : 3,
										"idRequest" : idRequest
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
					".saveCfu",
					function() {
						var idRequest = $(this).data(
						"idrequest");
						var cfu = $(this).closest("td")
						.children(".cfuToValidate")
						.val();
						if (idRequest != undefined
								&& idRequest > 0
								&& cfu != undefined && cfu < 13) {
							if (confirm("Conferma l'inserimento di "
									+ cfu
									+ " cfu per questa richiesta?")) {
								$(".preloader").show();

								$
								.ajax({
									url : absolutePath
									+ "/ServletSecretary",
									type : "POST",
									dataType : 'JSON',
									async : false,
									data : {
										"flag" : 2,
										"idRequest" : idRequest,
										"cfu" : cfu
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
							showAlert(1,
									"Si prega di compilare correttamente tutti i campi.");
						}

					});
		});

function showData() {
	$(".preloader").show();

	$.ajax({
		url : absolutePath + "/ServletSecretary",
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
				$("#bodySegretaryBody").html(msg.content);
			}
		},
		error : function(msg) {
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});

	$(".preloader").hide();
}
