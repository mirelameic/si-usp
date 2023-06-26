import 'package:flutter/material.dart';
import 'package:rp_project/routes.dart';
import 'package:rp_project/widgets/clickable_card.dart';
import 'package:rp_project/widgets/nav-drawer.dart';
import 'package:rp_project/widgets/roadmap-progress.dart';

class MyRoadmapsScreen extends StatelessWidget {
  void _addNewRoadmap(context) {
    // TODO
  }

  void _addNewCustomRoadmap(context) {
    Navigator.of(context).pushNamed(Routes.newCustomRoadmap);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: NavDrawer(),
      appBar: AppBar(title: Text('Meus Roadmaps')),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          ClickableCardWidget(
            title: 'React Basico',
            sufixWidget: RoadmapProgressWidget(
              progress: '10%',
              checkpoint: 'Ontem',
            ),
            onTap: () => _addNewRoadmap(context),
          ),
          ClickableCardWidget(
            title: 'React Avançado',
            sufixWidget: RoadmapProgressWidget(
              progress: '25%',
              checkpoint: '27/10/2021',
            ),
            onTap: () => _addNewRoadmap(context),
          ),
          ClickableCardWidget(
            title: 'Adicionar um novo roadmap',
            prefixWidget: Icon(Icons.add_circle_outline_outlined),
            sufixWidget: SizedBox(),
            description: 'Bora começar mais uma trilha?',
            onTap: () => _addNewRoadmap(context),
          ),
          ClickableCardWidget(
            title: 'Criar roadmap customizado',
            prefixWidget: Icon(Icons.add_circle_outline_outlined),
            sufixWidget: SizedBox(),
            description: 'Bora começar mais uma trilha?',
            onTap: () => _addNewCustomRoadmap(context),
          ),
        ],
      ),
    );
  }
}
