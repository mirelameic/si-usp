import 'package:flutter/material.dart';

class CardWidget extends StatelessWidget {
  final String title;
  final Widget? sufixWidget;
  final Widget? prefixWidget;

  CardWidget({
    required this.title,
    this.sufixWidget,
    this.prefixWidget,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      child: InkWell(
        onTap: () {},
        child: SizedBox(
          height: 70,
          width: double.infinity,
          child: Padding(
            padding: const EdgeInsets.only(left: 10),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                if (this.prefixWidget != null)
                  Padding(
                    padding: const EdgeInsets.only(left: 10, right: 5),
                    child: this.prefixWidget,
                  ),
                Padding(
                  padding: const EdgeInsets.only(left: 15, top: 5),
                  child: Text(
                    this.title,
                    style: TextStyle(fontSize: 18),
                  ),
                ),
                if (this.sufixWidget != null)
                  Padding(
                    padding: const EdgeInsets.only(right: 15),
                    child: Align(
                      alignment: Alignment.centerRight,
                      child: this.sufixWidget,
                    ),
                  )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
