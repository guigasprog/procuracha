**O que é DTO**

DTO, tambem conhecido como o Objeto de Transferência de Dados,
ele serve como um JSON montado de acordo com o seu uso,

Exemplo:

Entidade Cidade:
id, nome, id_uf;

Entidade Estado:
id, nome;

Cidade esta relacionado com Estado logo seu JSON seria:

{
    id = ?,
    nome = ?,
    id_uf = {
        id = ?,
        nome = ?
    }
}

mas pode existir mundos aonde voce pode fazer da seguinte forma manipulando o DTO

{
    id = ?,
    nome = ?,
    cidades = [{
        id = ?,
        nome = ?
    },{
        id = ?,
        nome = ?
    }]
}

no DTO de cidade não foi feito dessa forma.