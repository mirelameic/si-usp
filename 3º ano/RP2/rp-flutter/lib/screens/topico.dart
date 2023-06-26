import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:rp_project/routes.dart';

class topico extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Tópico',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Tópico X'),
          backgroundColor: Colors.grey,
        ),
        body: Topico(),
      ),
    );
  }
}

class Topico extends StatelessWidget {
  void goToQuiz(context) {
    Navigator.of(context).pushNamed(Routes.perguntasTopico);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        constraints: BoxConstraints.expand(
          height:
              Theme.of(context).textTheme.headline4!.fontSize! * 1.1 + 400.0,
        ),
        padding: const EdgeInsets.all(10.0),
        alignment: Alignment.center,
        child: Column(
          children: [
            Text('Lorem ipsum dolor sit amet. Qui dolores quis a quod pariatur est ipsam voluptas ut porro commodi. Et tenetur recusandae est blanditiis laudantium eum architecto molestiae.' +
                ' Non consequatur numquam vel mollitia labore eum magni reiciendis id velit voluptatem et repellendus pariatur. Qui inventore atque sed sint sunt et quis exercitationem et cupiditate quibusdam?' +
                ' Eum quisquam dolor eos tempora nisi non quae nulla! Ad porro alias qui culpa impedit cum omnis vero. Ut rerum similique quo omnis dicta non officiis modi non pariatur tempora qui consequatur commodi ea velit quidem.' +
                ' Id iusto expedita aut sint corporis ea rerum molestiae ut error ipsum. Et voluptatum vel beatae nulla aut iste facilis.'),
          ElevatedButton(onPressed: ()=>goToQuiz(context), child: Text('Continuar'))
          ],
        ),
        );
  }
}
