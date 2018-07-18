import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBureau } from 'app/shared/model/bureau.model';
import { BureauService } from './bureau.service';
import { IPersonne } from 'app/shared/model/personne.model';
import { PersonneService } from 'app/entities/personne';

@Component({
    selector: 'jhi-bureau-update',
    templateUrl: './bureau-update.component.html'
})
export class BureauUpdateComponent implements OnInit {
    private _bureau: IBureau;
    isSaving: boolean;

    personnes: IPersonne[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private bureauService: BureauService,
        private personneService: PersonneService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bureau }) => {
            this.bureau = bureau;
        });
        this.personneService.query().subscribe(
            (res: HttpResponse<IPersonne[]>) => {
                this.personnes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.bureau.id !== undefined) {
            this.subscribeToSaveResponse(this.bureauService.update(this.bureau));
        } else {
            this.subscribeToSaveResponse(this.bureauService.create(this.bureau));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBureau>>) {
        result.subscribe((res: HttpResponse<IBureau>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPersonneById(index: number, item: IPersonne) {
        return item.id;
    }
    get bureau() {
        return this._bureau;
    }

    set bureau(bureau: IBureau) {
        this._bureau = bureau;
    }
}
