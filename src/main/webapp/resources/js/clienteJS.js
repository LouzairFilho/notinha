function trocaPfPj(idFrom) {

	var tipoPessoa;
	if (document.getElementById) {
		tipoPessoa = idFrom.value;
	}


	var cnpj = "JURIDICA";
	var cpf = "FISICA";
	var elCpfCnpj = document.getElementById('cpfCnpj');

	if (tipoPessoa == cnpj) {
		document.getElementById("lbDataNasc").innerHTML = "Data de Abertura";
		document.getElementById("lbnomeRazao").innerHTML = "Razão Social";
		document.getElementById("lbCpfCnpj").innerHTML = "CNPJ";
		document.getElementById("lbRgIe").innerHTML = "I.E.";
		document.getElementById("cpfCnpj").value = " ";
		elCpfCnpj.setAttribute("onkeypress", "mascara(this,cnpj)");
		elCpfCnpj.setAttribute("maxLength", "18");

	}
	if (tipoPessoa == cpf) {
		document.getElementById("lbDataNasc").innerHTML = "Data Nascimento";
		document.getElementById("lbnomeRazao").innerHTML = "Nome";
		document.getElementById("lbCpfCnpj").innerHTML = "CPF";
		document.getElementById("lbRgIe").innerHTML = "RG";
		document.getElementById("cpfCnpj").value = " ";
		elCpfCnpj.setAttribute("onkeypress", "mascara(this,cpf)");
		elCpfCnpj.setAttribute("maxLength", "14");

	}
}
