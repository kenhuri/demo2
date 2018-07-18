import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPoste } from 'app/shared/model/poste.model';
import { Principal } from 'app/core';
import { PosteService } from './poste.service';

@Component({
    selector: 'jhi-poste',
    templateUrl: './poste.component.html'
})
export class PosteComponent implements OnInit, OnDestroy {
    postes: IPoste[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private posteService: PosteService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.posteService.query().subscribe(
            (res: HttpResponse<IPoste[]>) => {
                this.postes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPostes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPoste) {
        return item.id;
    }

    registerChangeInPostes() {
        this.eventSubscriber = this.eventManager.subscribe('posteListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
