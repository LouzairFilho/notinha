    function limpa_formulário_cep() {
            //Limpa valores do formulário de cep.
            document.getElementById('lagradouro').value=("");
            document.getElementById('bairro').value=("");
            document.getElementById('cidade').value=("");
            document.getElementById('estado').value=("");
    }

    function meu_callback(conteudo) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
        	document.getElementById('cep').value=(conteudo.cep);
            document.getElementById('lagradouro').value=(conteudo.logradouro);
            document.getElementById('bairro').value=(conteudo.bairro);
            document.getElementById('cidade').value=(conteudo.localidade);
            document.getElementById('estado').value=(conteudo.uf);
        } 
    }
     
    function buscarCEP(o,f){
        v_obj=o
        v_fun=f
        valorCampo = o.value;
        document.getElementById('cep').value=(valorCampo);
        setTimeout("execbusca()",1)
    }

    
    
    function execbusca(){
        v_obj.value=v_fun(v_obj.value)
    }
    function pesquisacep(valor) {

        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g,"")
        
        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {
           


                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = '//viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            } //end if.
            else {
                //cep é inválido.
//                limpa_formulário_cep();
//                alert("Formato de CEP inválido.");
                  document.getElementById('cep').value=(valorCampo);
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
           // limpa_formulário_cep();
              document.getElementById('cep').value=(valorCampo);
        }
    }