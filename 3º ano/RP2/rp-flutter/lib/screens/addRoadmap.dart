import 'package:flutter/material.dart';
import 'package:rp_project/routes.dart';

class AddRoadmap extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Add Roadmap',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Adicionar Roadmap'),
          backgroundColor: Colors.grey,
          actions: <Widget>[
          IconButton(
            icon: Icon(Icons.search),
            onPressed: () {},
          ),
          ]
      ),
      body: ListaRoadmaps(),
    ),
    );
  }
}

class ListaRoadmaps extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        children:[
          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('React Básico'),
            ),
          ),
          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Flutter Básico'),
            ),
          ),
          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Git Básico'),
            ),
          ),
          Card(
            child: ListTile(
              leading: Icon(Icons.add),
              title: Text('Investimentos'),
            ),
          ),
        ],
      ),
    );
  }


}
