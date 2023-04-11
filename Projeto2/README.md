Para a arquitetura de armazenamento, usaremos a estrutura oferecida pelo MongoDB:

• Criaremos um banco de dados para armazenar todo o livro.
• Cada capítulo será uma coleção no MongoDB.
• Dentro de cada coleção (cada capítulo) teremos os documentos (versos) daquele 
capítulo.

Os dados do livro estão repletos de caracteres especiais por conta do idioma e esses caracteres não podem ser perdidos durante o processo de ingestão dos dados no Data Lake. A arquitetura da solução será explicada nos vídeos a seguir. Os dados serão fornecidos a você e foram extraídos do link abaixo:

https://www.jsdelivr.com/
Aqui o link direto para os dados:
https://cdn.jsdelivr.net/npm/quran-json

Este link traz material de referência para os dados que estamos usando (com os nomes dos capítulos):

https://quran411.com/
