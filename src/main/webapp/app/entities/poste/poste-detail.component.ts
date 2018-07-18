import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPoste } from 'app/shared/model/poste.model';

@Component({
    selector: 'jhi-poste-detail',
    templateUrl: './poste-detail.component.html'
})
export class PosteDetailComponent implements OnInit {
    poste: IPoste;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ poste }) => {
            this.poste = poste;
        });
    }

    previousState() {
        window.history.back();
    }
}
