import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBureau } from 'app/shared/model/bureau.model';

@Component({
    selector: 'jhi-bureau-detail',
    templateUrl: './bureau-detail.component.html'
})
export class BureauDetailComponent implements OnInit {
    bureau: IBureau;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bureau }) => {
            this.bureau = bureau;
        });
    }

    previousState() {
        window.history.back();
    }
}
