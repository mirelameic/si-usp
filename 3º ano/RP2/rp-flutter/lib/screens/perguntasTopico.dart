import 'package:flutter/material.dart';
import 'package:rp_project/routes.dart';

class perguntasTopico extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Perguntas',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Quiz'),
          backgroundColor: Colors.grey,
      ),
      body: ListaPerguntas(),
    ),
    );
  }
}

class ListaPerguntas extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        children:[
          Card(
            child: Center(
              child: Text('Pergunta X: Lorem ipsum dolor sit amet. Qui dolores quis a quod pariatur est ipsam voluptas ut porro commodi. Et tenetur recusandae est blanditiis laudantium eum architecto molestiae.'
        + ' Non consequatur numquam vel mollitia labore eum magni reiciendis id velit voluptatem et repellendus pariatur. Qui inventore atque sed sint sunt et quis exercitationem et cupiditate quibusdam?'
        + ' Eum quisquam dolor eos tempora nisi non quae nulla! Ad porro alias qui culpa impedit cum omnis vero. Ut rerum similique quo omnis dicta non officiis modi non pariatur tempora qui consequatur commodi ea velit quidem.'
        + ' Id iusto expedita aut sint corporis ea rerum molestiae ut error ipsum. Et voluptatum vel beatae nulla aut iste facilis.'
        ))),
          Card(
            child: ListTile(
              title: Center(child: Text('Resposta 1')),
            ),
          ),
          Card(
            child: ListTile(
              title: Center(child: Text('Resposta 2')),
            ),
          ),
          Card(
            child: ListTile(
              title: Center(child: Text('Resposta 3')),
            ),
          ),
          Card(
            child: ListTile(
              title: Center(child: Text('Resposta 4')),
            ),
          ),
        ],
      ),
    );
  }


}
