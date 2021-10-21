# Make Magic - Venha fazer a magia acontecer como em Hogwarts.
## Aplicação teste desenvolviada para a empresa Dextra.

### Vale ressaltar que a IDE que estou utilizando para trabalho é o InteliJ IDEA

![gifcomputacao](https://github.com/Gabrielsbu/Gifs/blob/main/comecando.gif)

### Primeiro precisamos realizar algumas configurações.

#### 1) Crie um novo ambiente virtual, execute o seguinte comando: ```python -m venv "nome_do_ambiente"```, ao criar, existe um arquivo chamado requirements.txt e nele você encontrará todas as depêndencias necessárias.
#### 2) No seu ambiente virtual, abra a pasta chamada scripts, utilizando o terminal e execute o comando: ```activate```, assim sua VENV(Ambiente virtual) estará ativo. Em seguida, dentro da sua VENV e na raíz do projeto, execute o comando: ```pip install -r requirements.txt```
#### 3) Após isso, tudo certo, dentro da pasta da aplicação, execute o comando, ```python manage.py makemigrations```, em seguida, execute: ```python manage.py migrate``` para criar seu banco de dados.
#### 4) Você deve criar uma conta na Interface Administrativa para cadastrar especialidades, médicos e agendas. execute o comando ```python manage.py createsuperuser``` e cadastre.
#### 5) Agora com todo nosso ambiente pronto e instalado, execute o comando ```python manage.py runserver```, e finalmente a api estará pronta para ser consumida.

### Contribuintes
#### - [Gabriel Maia](https://github.com/Gabrielsbu)

### Links importantes que facilitarão a sua vida ;)
#### - [Insomnia] - <a id="raw-url" href="https://raw.githubusercontent.com/github-username/project/master/make-magic-insomnia">Download FILE</a>
