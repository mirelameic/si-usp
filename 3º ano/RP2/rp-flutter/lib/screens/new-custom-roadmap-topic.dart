import 'package:flutter/material.dart';
import 'package:rp_project/routes.dart';
import 'package:rp_project/widgets/clickable_card.dart';
import 'package:rp_project/widgets/nav-drawer.dart';

class NewCustomRoadmapTopic extends StatelessWidget {
  final List<String> addedTopics = ['React Básico', 'Startup enxuta'];

  void _gotoConfigureScreen(context) {
    Navigator.of(context).pushNamed(Routes.newCustomRoadmapConfigure);
  }

  void _gotoCreateTopicScreen(context) {
    Navigator.of(context).pushNamed(Routes.newCustomRoadmapCreateTopic);
  }

  List<Widget> _getAddedTopics() {
    return addedTopics
        .map(
          (topic) => new ClickableCardWidget(
            title: topic,
            sufixWidget: Icon(Icons.remove_circle_outline_outlined),
            onTap: () {},
          ),
        )
        .toList();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: NavDrawer(),
      appBar: AppBar(title: Text('Tópicos do roadmap')),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          SizedBox(
            height: 10,
          ),
          Padding(
            padding: const EdgeInsets.all(15),
            child: Center(
              child: Text(
                  'Agora é a hora de adiconar os tópicos e lições que compõe seu novo roadmap. Você pode adicionar quantos tópicos quiser, lembre de deixar eles bem completos e claro pra todo mundo que quiser aprender com você conseguir entender também'),
            ),
          ),
          ..._getAddedTopics(),
          ClickableCardWidget(
            title: 'Adicionar novo tópico',
            sufixWidget: Icon(Icons.add_circle_outline_outlined),
            onTap: () => _gotoCreateTopicScreen(context),
          ),
          Padding(
            padding: const EdgeInsets.all(15),
            child: ElevatedButton(
              onPressed: () => _gotoConfigureScreen(context),
              child: Text('Continuar'),
            ),
          ),
        ],
      ),
    );
  }
}
