# Sistema de controle de consultas médicas.
## Aplicação Medicar, desafio para empresa Intmed, Backend desenvolvido com Django Rest e Frontend desenvolvido com Angular 10.

### Vale ressaltar que estou ultilizando Windows 10, se tiver utilizando linux, haverá algumas alterações apenas na forma de ativação do seu ambiente virtual, certo?

![gifcomputacao](https://github.com/Gabrielsbu/Gifs/blob/main/comecando.gif)

### Primeiro precisamos realizar algumas configurações.

### Backend:
#### 1) Crie um novo ambiente virtual, execute o seguinte comando: ```python -m venv "nome_do_ambiente"```, ao criar, existe um arquivo chamado requirements.txt e nele você encontrará todas as depêndencias necessárias.
#### 2) No seu ambiente virtual, abra a pasta chamada scripts, utilizando o terminal e execute o comando: ```activate```, assim sua VENV(Ambiente virtual) estará ativo. Em seguida, dentro da sua VENV e na raíz do projeto, execute o comando: ```pip install -r requirements.txt```
#### 3) Após isso, tudo certo, dentro da pasta da aplicação, execute o comando, ```python manage.py makemigrations```, em seguida, execute: ```python manage.py migrate``` para criar seu banco de dados.
#### 4) Você deve criar uma conta na Interface Administrativa para cadastrar especialidades, médicos e agendas. execute o comando ```python manage.py createsuperuser``` e cadastre.
#### 5) Agora com todo nosso ambiente pronto e instalado, execute o comando ```python manage.py runserver```, e finalmente a api estará pronta para ser consumida.

### Frontend:
#### Passo 1) Abra a pasta do projeto em algum terminal, seja ele, PowerShell, CMD, Terminal do linux.
#### Passo 2) Aberto o projeto no seu terminal, digite o seguinte comando: ```yarn```, ou digite o comando: ```npm install```.
#### Observação) Dado o comando yarn, todas as depêndencias estarão prontas para serem utilizadas na sua máquina. Isso ocorre por conta da pasta node_modules (Pasta onde fica todas as depêndencias do projeto), ser muito grande, ela é inserida no git ignore, se não ficaria um projeto gigante.
#### Passo 3) Com isso feito, execute ```ng serve --o```


### Problemas
#### Em [issues]() é possível ver uma lista de alguns problemas que obtive durante o desenvolvimento do projeto e como corrigi.

### Contribuintes
#### - [Gabriel Maia](https://github.com/Gabrielsbu)

### Tarefas pendentes
#### - Backend - Desenvolver os filtros no Backend por falta de prática com o framework, os filtros que utilizei foram criados no frontend.
#### - Frontend - Fazer o usuário poder logar com o username ou com o Email, atualmente só está o username.
#### - Backend - Verificações com a hora, por conta que eu não consegui resolver o problema das 3 horas de diferença e eu não queria enviar com esse bug.
