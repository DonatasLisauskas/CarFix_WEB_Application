import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.rout = this.activatedRoute.routeConfig.outlet;
  }

  openNav(): void {
    document.getElementById('mySidenav').style.width = '15%';
  }

  closeNav(): void {
    document.getElementById('mySidenav').style.width = '0';
  }

}
