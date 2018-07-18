import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPersonne } from 'app/shared/model/personne.model';

@Component({
    selector: 'jhi-personne-detail',
    templateUrl: './personne-detail.component.html'
})
export class PersonneDetailComponent implements OnInit {
    personne: IPersonne;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ personne }) => {
            this.personne = personne;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
