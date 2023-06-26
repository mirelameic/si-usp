import 'package:flutter/material.dart';
import 'package:rp_project/widgets/bagde.dart';
import 'package:rp_project/widgets/clickable_card.dart';
import 'package:rp_project/widgets/header.dart';
import 'package:rp_project/widgets/nav-drawer.dart';

class MyAchivementsScreen extends StatelessWidget {
  void _onTap(context, type, achivementInfo) {
    if (type == "Achivement") {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return Bagde(
            title: achivementInfo![0],
            descriptions: achivementInfo![1],
            text: "Fechar",
          );
        },
      );
    }
  }

  Widget build(BuildContext context) {
    return Scaffold(
      drawer: NavDrawer(),
      appBar: AppBar(title: Text('Minhas conquistas')),
      body: Column(crossAxisAlignment: CrossAxisAlignment.stretch, children: [
        ClickableCardWidget(
          title: 'Hello, Roadmap',
          type: "Achivement",
          prefixWidget: Icon(Icons.add_circle_outline_outlined),
          achivementInfo: [
            'Hello, Roadmap',
            "Comecei a seguir meu primeiro roadmap no aplicativo"
          ],
          onTap: () => _onTap(context, "Achivement", [
            'Hello, Roadmap',
            "Comecei a seguir meu primeiro roadmap no aplicativo"
          ]),
        ),
        ClickableCardWidget(
          title: 'Eu faço meu caminho!',
          type: "Achivement",
          prefixWidget: Icon(Icons.add_circle_outline_outlined),
          achivementInfo: [
            "Eu faço meu caminho!",
            "Criei meu primeiro roadmap personalizado"
          ],
          onTap: () => _onTap(context, "Achivement", [
            "Eu faço meu caminho!",
            "Criei meu primeiro roadmap personalizado"
          ]),
        ),
        ClickableCardWidget(
          title: 'Conquista não alcançada ainda',
          sufixWidget: Icon(Icons.lock_outline),
          onTap: () {},
        ),
        ClickableCardWidget(
          title: 'Conquista não alcançada ainda',
          sufixWidget: Icon(Icons.lock_outline),
          onTap: () {},
        ),
      ]),
    );
  }
}
