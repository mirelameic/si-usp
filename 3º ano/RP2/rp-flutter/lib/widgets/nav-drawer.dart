import 'package:flutter/material.dart';
import 'package:rp_project/routes.dart';

class NavDrawer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: <Widget>[
          DrawerHeader(
            child: Text(
              'Oi, Want-Want!',
              style: TextStyle(color: Colors.white, fontSize: 25),
            ),
            decoration: BoxDecoration(
                color: Colors.green,
                image: DecorationImage(
                    fit: BoxFit.fill,
                    image: AssetImage('assets/images/pet.jpg'))),
          ),
          ListTile(
            leading: Icon(Icons.account_box),
            title: Text('Meu Perfil'),
            onTap: () => {},
          ),
          ListTile(
            leading: Icon(Icons.alt_route),
            title: Text('Meus Roadmaps'),
            onTap: () => {
              Navigator.of(context).pushReplacementNamed(Routes.myRoadmapScreen)
            },
          ),
          ListTile(
            leading: Icon(Icons.amp_stories),
            title: Text('Minhas conquistas'),
            onTap: () => {
              Navigator.of(context)
                  .pushReplacementNamed(Routes.myAchivementsScreen)
            },
          ),
        ],
      ),
    );
  }
}
